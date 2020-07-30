package com.am.films_list.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.films_list.BuildConfig
import com.am.films_list.common.SingleLiveEvent
import com.am.films_list.ui.State
import io.reactivex.observers.DisposableSingleObserver

class MoviesViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    val detail = SingleLiveEvent<Long>()

    val state: LiveData<State<List<MovieItemData>>>
        get() = stateInternal
    private val stateInternal = MutableLiveData<State<List<MovieItemData>>>()

    var isComplete: Boolean = false
        private set

    private var currentPage = 1
    private var maxPageSize = 1
    private var items = mutableListOf<MovieItemData>()

    init {
        getMovies(currentPage)
    }

    fun onItemClicked(id: Long) {
        detail.postValue(id)
    }

    fun loadNextMovies() {
        if (currentPage > maxPageSize) {
            isComplete = true
        } else {
            currentPage++
            getMovies(currentPage)
        }
    }

    private fun getMovies(page: Int) {
        getMoviesUseCase(BuildConfig.API_KEY, page).subscribe(
            object : DisposableSingleObserver<MoviesData>() {
                override fun onSuccess(moviesData: MoviesData) {
                    maxPageSize = moviesData.maxPageSize
                    items.addAll(moviesData.movies)
                    stateInternal.postValue(State.OnSuccess(items))
                }

                override fun onError(e: Throwable) {
                    stateInternal.postValue(State.OnError())
                }

                override fun onStart() {
                    stateInternal.postValue(State.Loading())
                }
            }
        )
    }

}
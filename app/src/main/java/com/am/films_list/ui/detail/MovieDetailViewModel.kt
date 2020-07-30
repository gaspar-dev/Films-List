package com.am.films_list.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.films_list.BuildConfig
import com.am.films_list.ui.State
import io.reactivex.observers.DisposableSingleObserver

class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {
    val state: LiveData<State<MovieDetail>>
        get() = stateInternal
    private val stateInternal = MutableLiveData<State<MovieDetail>>()

    fun getMovieDetail(id: Long) {
        getMovieDetailUseCase(BuildConfig.API_KEY, id).subscribe(
            object : DisposableSingleObserver<MovieDetail>() {
                override fun onSuccess(detailData: MovieDetail) {
                    stateInternal.postValue(State.OnSuccess(detailData))
                }

                override fun onError(e: Throwable) {
                    stateInternal.postValue(State.OnError())
                }
            }
        )
    }
}
package com.am.films_list.ui.main

import com.am.films_list.BuildConfig
import com.am.films_list.data.entity.Movie
import com.am.films_list.data.repository.MoviesApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetMoviesUseCase(private val moviesApiRepository: MoviesApiRepository) {

    operator fun invoke(appKey: String, page:Int): Single<MoviesData> {
        return moviesApiRepository.getPopularMovies(appKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                MoviesData(
                    result.movies.filter { !it.adult }
                        .map { movie ->
                            getMovieItem(movie)
                        },
                    result.totalPages
                )
            }
    }

    private fun getMovieItem(movie: Movie): MovieItemData {
        return MovieItemData(
            BuildConfig.IMAGE_URL + movie.posterPath,
            movie.originalTitle,
            movie.id
        )
    }
}
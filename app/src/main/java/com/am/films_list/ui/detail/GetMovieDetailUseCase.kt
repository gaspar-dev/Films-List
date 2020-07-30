package com.am.films_list.ui.detail

import com.am.films_list.BuildConfig
import com.am.films_list.data.repository.MoviesApiRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetMovieDetailUseCase(private val moviesApiRepository: MoviesApiRepository) {

    operator fun invoke(apiKey: String, id: Long): Single<MovieDetail> {
        return moviesApiRepository.getMovieDetail(apiKey, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result ->
                MovieDetail(
                    result.title,
                    result.budget,
                    BuildConfig.IMAGE_URL + result.posterPath,
                    result.originalLanguage
                )
            }
    }
}
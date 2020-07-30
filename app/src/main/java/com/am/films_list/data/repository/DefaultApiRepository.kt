package com.am.films_list.data.repository

import com.am.films_list.data.entity.DetailMovieResponse
import com.am.films_list.data.entity.PopularMovieResponse
import com.am.films_list.data.service.MoviesApiService
import io.reactivex.Single

class DefaultApiRepository(private val moviesApiService: MoviesApiService) : MoviesApiRepository {

    override fun getPopularMovies(apiKey: String, page: Int): Single<PopularMovieResponse> {
        return moviesApiService.getPopularMovies(apiKey, page)
    }

    override fun getMovieDetail(apiKey: String, id: Long): Single<DetailMovieResponse> {
        return moviesApiService.getMovieDetail(apiKey, id)
    }
}
package com.am.films_list.data.repository

import com.am.films_list.data.entity.DetailMovieResponse
import com.am.films_list.data.entity.PopularMovieResponse
import io.reactivex.Single

interface MoviesApiRepository {
    fun getPopularMovies(apiKey: String, page: Int): Single<PopularMovieResponse>
    fun getMovieDetail(apiKey: String, id: Long): Single<DetailMovieResponse>
}
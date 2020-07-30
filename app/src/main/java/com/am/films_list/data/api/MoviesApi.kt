package com.am.films_list.data.api

import com.am.films_list.data.entity.DetailMovieResponse
import com.am.films_list.data.entity.PopularMovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<PopularMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): Single<DetailMovieResponse>
}
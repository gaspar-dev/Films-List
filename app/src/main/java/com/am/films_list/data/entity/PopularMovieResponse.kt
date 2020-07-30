package com.am.films_list.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val movies: List<Movie>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int
)
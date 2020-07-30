package com.am.films_list.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailMovieResponse(

    @Json(name = "title")
    val title: String,

    @Json(name = "budget")
    val budget: Int,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "original_language")
    val originalLanguage: String
)
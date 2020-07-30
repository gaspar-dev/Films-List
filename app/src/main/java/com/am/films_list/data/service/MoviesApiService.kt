package com.am.films_list.data.service

import com.am.films_list.BuildConfig
import com.am.films_list.data.api.MoviesApi
import com.am.films_list.data.entity.DetailMovieResponse
import com.am.films_list.data.entity.PopularMovieResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.orhanobut.logger.Logger
import com.squareup.moshi.Moshi
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MoviesApiService {

    private val httpClient = OkHttpClient.Builder()
        .callTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request()
            val newBuilder = request.newBuilder()
            chain.proceed(newBuilder.build())
        }
        .addInterceptor(
            HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { Logger.d(it) }).setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )
        )
        .build()

    private val moshi = Moshi.Builder().build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    private val api = retrofit.create(MoviesApi::class.java)

    fun getPopularMovies(apiKey: String, page: Int): Single<PopularMovieResponse> {
        return api.getPopularMovies(apiKey, page)
    }

    fun getMovieDetail(apiKey: String, movieId: Long): Single<DetailMovieResponse> {
        return api.getMovieDetail(movieId, apiKey)
    }

}
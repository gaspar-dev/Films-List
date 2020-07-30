package com.am.films_list.di

import com.am.films_list.data.repository.DefaultApiRepository
import com.am.films_list.data.repository.MoviesApiRepository
import com.am.films_list.data.service.MoviesApiService
import com.am.films_list.ui.detail.GetMovieDetailUseCase
import com.am.films_list.ui.detail.MovieDetailViewModel
import com.am.films_list.ui.main.GetMoviesUseCase
import com.am.films_list.ui.main.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }

    factory { GetMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }

    single<MoviesApiRepository> { DefaultApiRepository(get()) }
    single { MoviesApiService() }
}
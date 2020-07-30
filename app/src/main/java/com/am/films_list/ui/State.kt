package com.am.films_list.ui

sealed class State<out T> {
    class OnSuccess<T>(val data: T) : State<T>()
    class Loading<T> : State<T>()
    class OnError<T> : State<T>()
}
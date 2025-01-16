package com.example.articapp.utils

sealed class Resource<T> {

    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val errorType: ErrorType): Resource<T>()

}
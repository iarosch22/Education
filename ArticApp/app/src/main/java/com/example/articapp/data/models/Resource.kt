package com.example.articapp.data.models

import com.example.articapp.utils.ErrorType

sealed class Resource<T> {

    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val errorType: ErrorType): Resource<T>()

}
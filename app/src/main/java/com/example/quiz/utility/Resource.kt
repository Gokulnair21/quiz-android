package com.example.quiz.utility

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorMessage: String? = null, data: T? = null) :
        Resource<T>(errorMessage = errorMessage)

    class Loading<T> : Resource<T>()

}
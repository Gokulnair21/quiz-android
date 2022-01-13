package com.example.quiz.data.network

import com.example.quiz.utility.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface QuestionsAPI {

    @GET("questions")
    suspend fun getQuestions(
        @Header("X-Api-Key") apiKey: String = Constants.API_KEY,
        @Query("category")category: String?=null,
        difficulty: String,
        limit: String = "10"
    )
}
package com.example.quiz.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = " https://quizapi.io/api/v1/"

    private val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getQuestionsAPI(): QuestionsAPI =  retrofit.create(QuestionsAPI::class.java)


}
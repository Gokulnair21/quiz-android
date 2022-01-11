package com.example.quiz.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = " https://quizapi.io/api/v1/"

    private val retrofit=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
        BASE_URL).build()

    fun getQuestionsAPI()  =  retrofit.create(QuestionsAPI::class.java)


}
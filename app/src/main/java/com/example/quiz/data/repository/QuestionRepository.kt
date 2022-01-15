package com.example.quiz.data.repository

import com.example.quiz.data.network.QuestionsAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionsAPI: QuestionsAPI) {


    suspend fun getQuestions() = questionsAPI.getQuestions()

}
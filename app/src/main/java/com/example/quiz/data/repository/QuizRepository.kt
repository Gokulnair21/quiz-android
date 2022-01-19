package com.example.quiz.data.repository

import com.example.quiz.data.database.QuizDAO
import com.example.quiz.data.model.Quiz
import javax.inject.Inject

class QuizRepository @Inject constructor(private val quizDAO: QuizDAO) {


    suspend fun getQuiz() = quizDAO.getAllQuiz()

    suspend fun insertQuiz(quiz: Quiz) = quizDAO.insertQuiz(quiz)

}
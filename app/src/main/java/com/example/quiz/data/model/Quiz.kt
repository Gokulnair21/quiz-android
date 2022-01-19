package com.example.quiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "QuizTable")
data class Quiz(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val totalQuestions: Int,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val category: String,
    val difficulty: String
)
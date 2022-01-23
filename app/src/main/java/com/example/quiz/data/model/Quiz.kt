package com.example.quiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "QuizTable")
data class Quiz(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    val totalQuestions: Int,
    val correctQuestions: Int,
    val wrongQuestions: Int,
    val category: String,
    val difficulty: String,
    val skippedQuestions: Int
)
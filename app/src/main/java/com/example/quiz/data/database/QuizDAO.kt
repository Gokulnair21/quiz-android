package com.example.quiz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz.data.model.Quiz


@Dao
interface QuizDAO {

    @Insert
    suspend fun insertQuiz(quiz: Quiz)

    @Query("Select * from QuizTable ORDER BY id DESC")
    suspend fun getAllQuiz():List<Quiz>

}

package com.example.quiz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quiz.data.model.Quiz
import dagger.hilt.android.qualifiers.ApplicationContext


@Database(entities = [Quiz::class], version = 1, exportSchema = false)
abstract class RoomDatabaseService() : RoomDatabase() {

    abstract fun getQuizDAO(): QuizDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseService? = null

        fun getDatabase(context: Context): RoomDatabaseService {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabaseService::class.java,
                    "quiz_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
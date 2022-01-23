package com.example.quiz.di

import android.content.Context
import android.content.SharedPreferences
import com.example.quiz.data.database.QuizDAO
import com.example.quiz.data.database.RoomDatabaseService
import com.example.quiz.data.network.QuestionsAPI
import com.example.quiz.data.network.RetrofitInstance
import com.example.quiz.data.repository.PreferenceRepository
import com.example.quiz.data.repository.QuestionRepository
import com.example.quiz.data.repository.QuizRepository
import com.example.quiz.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constants.USER_PREF, Context.MODE_PRIVATE
        )

    @Provides
    fun provideQuestionsAPI(): QuestionsAPI = RetrofitInstance.getQuestionsAPI()

    @Provides
    fun provideQuizDAO(@ApplicationContext context: Context): QuizDAO =
        RoomDatabaseService.getDatabase(context).getQuizDAO()


    @Singleton
    @Provides
    fun providePreferenceRepository(sharedPreferences: SharedPreferences): PreferenceRepository =
        PreferenceRepository(sharedPreferences)

    @Singleton
    @Provides
    fun provideQuestionRepository(questionsAPI: QuestionsAPI) = QuestionRepository(questionsAPI)

    @Singleton
    @Provides
    fun provideQuizRepository(quizDAO: QuizDAO) = QuizRepository(quizDAO)

}
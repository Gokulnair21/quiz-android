package com.example.quiz.di

import com.example.quiz.data.network.QuestionsAPI
import com.example.quiz.data.network.RetrofitInstance
import com.example.quiz.data.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideQuestionsAPI() = RetrofitInstance.getQuestionsAPI()


    @Singleton
    @Provides
    fun provideQuestionRepository(questionsAPI: QuestionsAPI)=QuestionRepository(questionsAPI)

}
package com.example.quiz.view.questions_page

import androidx.lifecycle.ViewModel
import com.example.quiz.data.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuestionPageViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {


        fun getQuestions(){

        }


}
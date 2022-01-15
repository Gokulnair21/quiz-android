package com.example.quiz.view.questions_page


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.data.model.Question
import com.example.quiz.data.repository.QuestionRepository
import com.example.quiz.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionPageViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {

    val questions = MutableStateFlow<Resource<List<Question>>>(Resource.Loading())
    val question = MutableStateFlow<Question?>(null)
    val currentQuestion = MutableStateFlow<Int>(0)
    val points = MutableStateFlow(0)

    private fun getQuestions() = viewModelScope.launch(Dispatchers.IO) {
        try {
            questions.value = Resource.Loading()
            val questionsList = questionRepository.getQuestions()
            Log.d("ViewModel", questionsList.toString())
            question.value = questionsList[currentQuestion.value]
            Log.d("ViewModel", questionsList.toString())
            questions.value = Resource.Success(questionsList)

        } catch (e: Exception) {
            questions.value = Resource.Error(e.message.toString())
        }
    }


    fun nextQuestion(answerStatus: Boolean) {
        currentQuestion.value = currentQuestion.value + 1
        question.value = questions.value.data?.get(currentQuestion.value)
        if (answerStatus) {
            points.value += 1
        }
    }

    init {
        getQuestions()
    }


}
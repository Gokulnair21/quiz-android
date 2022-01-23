package com.example.quiz.view.questions_page


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.data.model.Question
import com.example.quiz.data.repository.QuestionRepository
import com.example.quiz.utility.Constants
import com.example.quiz.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionPageViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val state: SavedStateHandle
) :
    ViewModel() {

    val questions = MutableStateFlow<Resource<List<Question>>>(Resource.Loading())
    val question = MutableStateFlow<Question?>(null)
    val currentQuestion = MutableStateFlow<Int>(0)
    val points = MutableStateFlow(0)
    val skippedQuestions = MutableStateFlow(0)

    private fun getQuestions(category: String, limit: String, difficulty: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                questions.value = Resource.Loading()
                val questionsList = questionRepository.getQuestions(
                    category = category,
                    difficulty = difficulty,
                    limit = limit
                )
                question.value = questionsList[currentQuestion.value]
                questions.value = Resource.Success(questionsList)

            } catch (e: Exception) {
                questions.value = Resource.Error(e.message.toString())
            }
        }


    fun nextQuestion(answerStatus: Boolean, answerValue: String) {
        currentQuestion.value = currentQuestion.value + 1
        question.value = questions.value.data?.get(currentQuestion.value)
        if (answerValue.isNotEmpty()) {
            if (answerStatus) {
                points.value += 1
            }
        } else {
            skippedQuestions.value += 1
        }
    }


    init {
        getQuestions(
            category = state.get<String>(Constants.CATEGORY_PARAM)!!,
            limit = state.get<String>(Constants.LIMIT_PARAM)!!,
            difficulty = state.get<String>(Constants.DIFFICULTY_PARAM)!!,
        )
    }

}
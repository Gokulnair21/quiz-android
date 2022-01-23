package com.example.quiz.view.result_page

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.data.model.Quiz
import com.example.quiz.data.repository.PreferenceRepository
import com.example.quiz.data.repository.QuizRepository
import com.example.quiz.utility.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResultPageViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val quizRepository: QuizRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    val name = MutableStateFlow("")

    private fun insertQuiz() = viewModelScope.launch {
        val quiz = Quiz(
            category = state.get<String>(Constants.CATEGORY_PARAM)!!,
            difficulty = state.get<String>(Constants.DIFFICULTY_PARAM)!!,
            totalQuestions = state.get<Int>(Constants.TOTAL_QUESTIONS_PARAM)!!,
            correctQuestions = state.get<Int>(Constants.CORRECT_QUESTIONS_PARAM)!!,
            wrongQuestions = (state.get<Int>(Constants.TOTAL_QUESTIONS_PARAM)!! - (state.get<Int>(
                Constants.CORRECT_QUESTIONS_PARAM
            )!! + state.get<Int>(Constants.SKIPPED_QUESTIONS_PARAM)!!)),
            skippedQuestions = state.get<Int>(Constants.SKIPPED_QUESTIONS_PARAM)!!
        )
        quizRepository.insertQuiz(quiz)
    }


    init {
        name.value = preferenceRepository.getUserName() ?: ""
        insertQuiz()
    }
}
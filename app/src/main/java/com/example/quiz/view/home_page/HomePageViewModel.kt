package com.example.quiz.view.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.data.model.Quiz
import com.example.quiz.data.repository.PreferenceRepository
import com.example.quiz.data.repository.QuizRepository
import com.example.quiz.utility.getAllCorrectQuestions
import com.example.quiz.utility.getAllSkippedQuestions
import com.example.quiz.utility.getAllWrongQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val preferenceRepository: PreferenceRepository
) :
    ViewModel() {

    val name = MutableStateFlow("")

    val quiz = MutableStateFlow<List<Quiz>>(listOf())
    val totalQuizzes = MutableStateFlow<Long>(0)
    val correctQuestions = MutableStateFlow<Long>(0)
    val skippedQuestions = MutableStateFlow<Long>(0)
    val wrongQuestions = MutableStateFlow<Long>(0)

    fun getQuiz() = viewModelScope.launch(Dispatchers.IO) {
        quiz.value = quizRepository.getQuiz()
        totalQuizzes.value = quiz.value.size.toLong()
        correctQuestions.value = quiz.value.getAllCorrectQuestions()
        wrongQuestions.value = quiz.value.getAllWrongQuestions()
        skippedQuestions.value = quiz.value.getAllSkippedQuestions()
    }

    init {
        name.value = preferenceRepository.getUserName() ?: ""
        getQuiz()
    }

}
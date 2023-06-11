package com.example.quiz.view.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val preferenceRepository: PreferenceRepository) :
    ViewModel() {

    suspend fun isNamePresent(): Boolean {
        val data = preferenceRepository.getUserName()
        delay(500) //for smoothness purpose
        return data.isNullOrEmpty()
    }
}
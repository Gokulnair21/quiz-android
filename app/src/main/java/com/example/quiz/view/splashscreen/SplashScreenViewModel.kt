package com.example.quiz.view.splashscreen

import androidx.lifecycle.ViewModel
import com.example.quiz.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val preferenceRepository: PreferenceRepository):ViewModel() {

    fun isNamePresent():Boolean{
      val data= preferenceRepository.getUserName()
        return data.isNullOrEmpty()
    }
}
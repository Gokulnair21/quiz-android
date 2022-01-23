package com.example.quiz.view.introduction_page


import androidx.lifecycle.ViewModel
import com.example.quiz.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class IntroductionPageViewModel @Inject constructor(private val preferenceRepository: PreferenceRepository) :
    ViewModel() {

    val name = MutableStateFlow("")
    val showError = MutableStateFlow(false)


    fun getValueForName(value: String) {
        name.value = value
    }


    fun onCLickLetsPlay(): Boolean {
        return if (name.value.isEmpty()) {
            showError.value = true
            false
        } else {
            showError.value = false
            preferenceRepository.setUserName(name.value)
        }
    }

}


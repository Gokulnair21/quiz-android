package com.example.quiz.view.introduction_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quiz.utility.Screen
import kotlinx.coroutines.flow.MutableStateFlow

class IntroductionPageViewModel():ViewModel() {

    val name = MutableStateFlow("")
    val showError=MutableStateFlow(false)


    fun getValueForName(value:String){
        name.value=value
    }


    fun onCLickLetsPlay():Boolean{
       return if (name.value.isEmpty()) {
            showError.value = true
           false
        } else {
            showError.value = false
           true
        }
    }

}


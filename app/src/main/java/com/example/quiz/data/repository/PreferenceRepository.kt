package com.example.quiz.data.repository

import android.content.SharedPreferences
import com.example.quiz.utility.Constants
import javax.inject.Inject

class PreferenceRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getUserName() = sharedPreferences.getString(Constants.USER_NAME, "")


    fun setUserName(name: String): Boolean {
        return try {
            val edit = sharedPreferences.edit()
            edit.putString(Constants.USER_NAME, name)
            edit.apply()
            true
        } catch (e: Exception) {
            false
        }
    }
}
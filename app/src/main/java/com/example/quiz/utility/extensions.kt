package com.example.quiz.utility

import com.example.quiz.R
import com.example.quiz.data.model.Category
import com.example.quiz.data.model.Quiz


fun List<Category>.getImageResource(value: String): Int {
    this.forEach {
        if (it.value == value) return it.imageID
    }
    return R.drawable.random
}


fun String.toCapitalize(): String = this.replaceFirstChar {
    it.uppercase()
}


fun List<Quiz>.getAllCorrectQuestions(): Long {
    var points: Long = 0
    this.forEach {
        points += it.correctQuestions
    }
    return points
}

fun List<Quiz>.getAllWrongQuestions(): Long {
    var points: Long = 0
    this.forEach {
        points += it.wrongQuestions
    }
    return points
}

fun List<Quiz>.getAllSkippedQuestions(): Long {
    var points: Long = 0
    this.forEach {
        points += it.skippedQuestions
    }
    return points
}
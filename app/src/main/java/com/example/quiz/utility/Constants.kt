package com.example.quiz.utility

import com.example.quiz.R
import com.example.quiz.data.model.Category

object Constants {

    const val API_KEY: String = "d5Zyev6wD0jZgw1d8EKE5QSiirrWVDAR9JPD6kAq"


    //Params
    const val CATEGORY_PARAM: String = "category"
    const val LIMIT_PARAM: String = "limit"
    const val DIFFICULTY_PARAM: String = "difficulty"
    const val TOTAL_QUESTIONS_PARAM = "total_questions"
    const val CORRECT_QUESTIONS_PARAM = "correct_questions"
    const val SKIPPED_QUESTIONS_PARAM = "skipped_questions"

    const val USER_NAME: String = "username"

    const val USER_PREF: String = "user_pref"


    val categories = listOf(
        Category(
            heading = "Linux",
            value = "linux",
            totalQuestions = 99,
            imageID = R.drawable.linux
        ),
        Category(
            heading = "Bash",
            value = "bash",
            totalQuestions = 77,
            imageID = R.drawable.bash
        ),
        Category(
            heading = "HTML",
            value = "html",
            totalQuestions = 139,
            imageID = R.drawable.html
        ),
        Category(
            heading = "Javascript",
            value = "javascript",
            totalQuestions = 25,
            imageID = R.drawable.javascript
        ),
        Category(
            heading = "Laravel",
            value = "laravel",
            totalQuestions = 10,
            imageID = R.drawable.laravel
        ),
        Category(
            heading = "Kubernetes",
            value = "kubernetes",
            totalQuestions = 136,
            imageID = R.drawable.kubernetes
        ),
        Category(
            heading = "MySQL",
            value = "mysql",
            totalQuestions = 153,
            imageID = R.drawable.mysql
        ),
        Category(
            heading = "PHP",
            value = "php",
            totalQuestions = 181,
            imageID = R.drawable.php
        ),
        Category(
            heading = "Wordpress",
            value = "wordpress",
            totalQuestions = 66,
            imageID = R.drawable.wordpress
        ),
        Category(
            heading = "DevOps",
            value = "devops",
            totalQuestions = 23,
            imageID = R.drawable.devops
        ),
        Category(
            heading = "Docker",
            value = "docker",
            totalQuestions = 125,
            imageID = R.drawable.docker
        )
    )

}
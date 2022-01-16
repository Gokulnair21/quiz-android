package com.example.quiz.utility

sealed class Screen(val route: String) {
    object IntroductionPage : Screen("introduction_page")
    object HomePage : Screen("home_page")
    object SplashScreen : Screen("splash_screen")
    object QuestionsConfigurationPage : Screen("questions_configuration_page/{category}") {
        fun createRoute(category: String): String =
            "questions_configuration_page/$category"
    }

    object QuestionsPage : Screen("questions_page/{category}/{difficulty}/{limit}") {
        fun createRoute(category: String, limit: String, difficulty: String) =
            "questions_page/$category/$difficulty/$limit"
    }

    object ResultPage : Screen("result_page/{correct_questions}/{total_questions}") {
        fun createRoute(totalQuestions: Int, correctQuestions: Int): String =
            "result_page/$correctQuestions/$totalQuestions"
    }
}
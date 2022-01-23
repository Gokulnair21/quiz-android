package com.example.quiz.utility

sealed class Screen(val route: String) {
    object IntroductionPage : Screen("introduction_page")
    object HomePage : Screen("home_page")
    object CategoryPage : Screen("category_page")
    object SplashScreen : Screen("splash_screen")
    object QuestionsConfigurationPage :
        Screen("questions_configuration_page/{${Constants.CATEGORY_PARAM}}") {
        fun createRoute(category: String): String =
            "questions_configuration_page/$category"
    }

    object QuestionsPage :
        Screen("questions_page/{${Constants.CATEGORY_PARAM}}/{${Constants.DIFFICULTY_PARAM}}/{${Constants.LIMIT_PARAM}}") {
        fun createRoute(category: String, limit: String, difficulty: String) =
            "questions_page/$category/$difficulty/$limit"
    }

    object ResultPage :
        Screen("result_page/{${Constants.CATEGORY_PARAM}}/{${Constants.DIFFICULTY_PARAM}}/{${Constants.CORRECT_QUESTIONS_PARAM}}/{${Constants.TOTAL_QUESTIONS_PARAM}}/{${Constants.SKIPPED_QUESTIONS_PARAM}}") {
        fun createRoute(
            category: String,
            difficulty: String,
            totalQuestions: Int,
            correctQuestions: Int,
            skippedQuestions: Int
        ): String =
            "result_page/$category/$difficulty/$correctQuestions/$totalQuestions/$skippedQuestions"
    }
}
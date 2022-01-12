package com.example.quiz.utility

sealed class Screen(val route: String) {
    object IntroductionPage : Screen("introduction_page")
    object HomePage : Screen("home_page")
    object SplashScreen : Screen("splash_screen")
    object QuestionsConfigurationPage : Screen("questions_configuration_page")
    object QuestionsPage : Screen("questions_page")
}
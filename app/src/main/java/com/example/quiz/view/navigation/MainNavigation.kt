package com.example.quiz.view.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.utility.Screen
import com.example.quiz.view.home_page.HomePage
import com.example.quiz.view.introduction_page.IntroductionPage
import com.example.quiz.view.questions_page.QuestionsPage
import com.example.quiz.view.splashscreen.SplashScreen

@ExperimentalFoundationApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.IntroductionPage.route) {
            IntroductionPage(navController)
        }
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.HomePage.route) {
            HomePage(navController)
        }
        composable(Screen.QuestionsPage.route) {
            QuestionsPage(navController)
        }
    }
}
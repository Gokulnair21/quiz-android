package com.example.quiz.view.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiz.utility.Screen
import com.example.quiz.view.ResultPage
import com.example.quiz.view.home_page.HomePage
import com.example.quiz.view.introduction_page.IntroductionPage
import com.example.quiz.view.question_configuration.QuestionConfigurationPage
import com.example.quiz.view.questions_page.QuestionsPage
import com.example.quiz.view.splashscreen.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
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
        composable(Screen.QuestionsConfigurationPage.route, arguments = listOf(
            navArgument("category") { type = NavType.StringType }
        )) {
            QuestionConfigurationPage(navController)
        }
        composable(Screen.ResultPage.route, arguments = listOf(
            navArgument("correct_questions") { type = NavType.IntType },
            navArgument("total_questions") { type = NavType.IntType }
        )) {
            ResultPage(
                navController = navController,
                correctQuestions = it.arguments!!.getInt("correct_questions"),
                totalQuestions = it.arguments!!.getInt("total_questions")
            )
        }
    }
}

//arguments = listOf(
//navArgument("category") { type = NavType.StringType },
//navArgument("difficult") { type = NavType.StringType },
//navArgument("limit") { type = NavType.StringType }
//)
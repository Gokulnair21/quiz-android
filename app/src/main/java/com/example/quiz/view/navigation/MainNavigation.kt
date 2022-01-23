package com.example.quiz.view.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiz.utility.Constants
import com.example.quiz.utility.Screen
import com.example.quiz.view.category_page.CategoryPage
import com.example.quiz.view.result_page.ResultPage
import com.example.quiz.view.home_page.HomePage
import com.example.quiz.view.home_page.HomePageViewModel
import com.example.quiz.view.introduction_page.IntroductionPage
import com.example.quiz.view.question_configuration.QuestionConfigurationPage
import com.example.quiz.view.questions_page.QuestionsPage
import com.example.quiz.view.splashscreen.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun MainNavigation() {

    val homePageViewModel: HomePageViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.IntroductionPage.route) {
            IntroductionPage(navController)
        }
        composable(Screen.HomePage.route) {
            HomePage(navController, homePageViewModel)
        }
        composable(Screen.CategoryPage.route) {
            CategoryPage(navController)
        }
        composable(Screen.QuestionsPage.route, arguments = listOf(
            navArgument(Constants.CATEGORY_PARAM) { type = NavType.StringType },
            navArgument(Constants.LIMIT_PARAM) { type = NavType.StringType },
            navArgument(Constants.DIFFICULTY_PARAM) { type = NavType.StringType }
        )) {
            QuestionsPage(
                navController = navController,
                category = it.arguments!!.getString(Constants.CATEGORY_PARAM)!!,
                difficulty = it.arguments!!.getString(Constants.DIFFICULTY_PARAM)!!,
                limit = it.arguments!!.getString(Constants.LIMIT_PARAM)!!
            )
        }
        composable(Screen.QuestionsConfigurationPage.route, arguments = listOf(
            navArgument(Constants.CATEGORY_PARAM) { type = NavType.StringType }
        )) {
            QuestionConfigurationPage(
                navController = navController,
                category = it.arguments!!.getString(Constants.CATEGORY_PARAM)!!
            )
        }
        composable(Screen.ResultPage.route, arguments = listOf(
            navArgument(Constants.CATEGORY_PARAM) { type = NavType.StringType },
            navArgument(Constants.DIFFICULTY_PARAM) { type = NavType.StringType },
            navArgument(Constants.CORRECT_QUESTIONS_PARAM) { type = NavType.IntType },
            navArgument(Constants.TOTAL_QUESTIONS_PARAM) { type = NavType.IntType },
            navArgument(Constants.SKIPPED_QUESTIONS_PARAM) { type = NavType.IntType }
        )) {
            ResultPage(
                navController = navController,
                correctQuestions = it.arguments!!.getInt(Constants.CORRECT_QUESTIONS_PARAM),
                totalQuestions = it.arguments!!.getInt(Constants.TOTAL_QUESTIONS_PARAM),
                homePageViewModel = homePageViewModel
            )
        }
    }
}


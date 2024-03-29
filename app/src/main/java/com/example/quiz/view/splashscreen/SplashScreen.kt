package com.example.quiz.view.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quiz.data.repository.PreferenceRepository
import com.example.quiz.utility.Screen
import kotlinx.coroutines.delay
import javax.inject.Inject

@Composable
fun SplashScreen(
    navController: NavController,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true, block = {
        if (splashScreenViewModel.isNamePresent()) {
            navController.navigate(Screen.IntroductionPage.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }

    })
    Surface(color = MaterialTheme.colors.primary) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "QUIZ",
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}
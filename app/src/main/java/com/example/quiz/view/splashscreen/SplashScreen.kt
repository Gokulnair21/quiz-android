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
import androidx.navigation.NavController
import com.example.quiz.utility.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true, block = {
        delay(3000)
        navController.navigate(Screen.QuestionsPage.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
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
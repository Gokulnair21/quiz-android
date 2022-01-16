package com.example.quiz.view.result_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quiz.R
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton

@Composable
fun ResultPage(navController: NavController, correctQuestions: Int, totalQuestions: Int) {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Quiz Result",
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = "Trophy",
                    modifier = Modifier
                        .size((LocalConfiguration.current.screenWidthDp / 2).dp)
                        .padding(vertical = 30.dp)
                )
                Text(
                    text = "Congratulations",
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 5.dp)
                )

                Text(
                    text = "YOUR SCORE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 5.dp),
                    color = MaterialTheme.colors.onPrimary.copy(0.5F)
                )
                Text(
                    text = "$correctQuestions / $totalQuestions",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 5.dp)
                )
                CustomButton(heading = "Take new Quiz") {
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.ResultPage.route) {
                            inclusive = true
                        }

                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewResultPage() {
    ResultPage(navController = rememberNavController(), 20, 20)
}
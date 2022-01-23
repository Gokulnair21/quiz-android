package com.example.quiz.view.result_page

import android.text.style.AlignmentSpan
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quiz.R
import com.example.quiz.data.repository.PreferenceRepository
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton
import com.example.quiz.view.home_page.HomePageViewModel

@Composable
fun ResultPage(
    navController: NavController,
    correctQuestions: Int,
    totalQuestions: Int,
    resultPageViewModel: ResultPageViewModel = hiltViewModel(),
    homePageViewModel: HomePageViewModel
) {

    val name by resultPageViewModel.name.collectAsState()


    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1F))
                    IconButton(
                        onClick = {
                            homePageViewModel.getQuiz()
                            navController.navigateUp()
                        },
                    ) {
                        Card(
                            shape = CircleShape,
                            backgroundColor = MaterialTheme.colors.secondary
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close",
                                tint = MaterialTheme.colors.onSecondary,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(30.dp)
                            )
                        }
                    }
                }
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
                if (name.isNotEmpty()) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Normal,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    )

                }
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
                    homePageViewModel.getQuiz()
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.ResultPage.route) {
                            inclusive = true
                        }

                    }
                }
            }
        }

    }
    BackHandler() {
        homePageViewModel.getQuiz()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewResultPage() {
}
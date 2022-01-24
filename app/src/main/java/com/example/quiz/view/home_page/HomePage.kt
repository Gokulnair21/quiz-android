package com.example.quiz.view.home_page

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quiz.R
import com.example.quiz.data.model.Category
import com.example.quiz.data.model.CorrectAnswers
import com.example.quiz.data.model.Quiz
import com.example.quiz.ui.theme.amber
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton


@ExperimentalFoundationApi
@Composable
fun HomePage(navController: NavController, homePageViewModel: HomePageViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()
    val name by homePageViewModel.name.collectAsState()
    val quizzes by homePageViewModel.quiz.collectAsState()
    val totalQuizzes by homePageViewModel.totalQuizzes.collectAsState()
    val correctQuestions by homePageViewModel.correctQuestions.collectAsState()
    val wrongQuestions by homePageViewModel.wrongQuestions.collectAsState()
    val skippedQuestions by homePageViewModel.skippedQuestions.collectAsState()
    if (quizzes.isEmpty()) {

      Surface(
          color = MaterialTheme.colors.primary,
      ) {
          Column(
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                  .fillMaxSize()
                  .padding(horizontal = 20.dp)
          ) {
              Image(
                  painter = painterResource(id = R.drawable.empty),
                  contentDescription = "Empty List",
                  modifier = Modifier
                      .fillMaxWidth()
                      .height((LocalConfiguration.current.screenWidthDp).dp)
              )
              Text(text = "Hello $name, \nSeems like you haven't played any game. So let's proceed to an one quick game.", fontSize = 18.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
              Spacer(modifier = Modifier.height(30.dp))
              CustomButton(heading = "New Quiz") {
                  navController.navigate(Screen.CategoryPage.route)
              }
          }
      }
    } else {


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.primary,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(20.dp)
                ) {

                    Text(
                        text = "HELLO",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(text = name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    QuizCardOverView(
                        totalQuizzes = totalQuizzes,
                        correctQuestions = correctQuestions,
                        wrongQuestions = wrongQuestions,
                        skippedQuestions = skippedQuestions
                    )
                    if (quizzes.isNotEmpty()) {
                        PreviousQuizInformation(quizzes)
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate(Screen.CategoryPage.route) },
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary,
                    icon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add",
                        )
                    },
                    text = { Text(text = "New Quiz", fontWeight = FontWeight.Bold) }
                )
            }
        )
    }
}

@Composable
fun QuizCardOverView(
    totalQuizzes: Long,
    correctQuestions: Long,
    wrongQuestions: Long,
    skippedQuestions: Long
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            SummaryHeading("Rounds")
            SummaryValues(
                size = 70,
                color = MaterialTheme.colors.secondary,
                value = totalQuizzes.toString(),
                fontSize = 30
            )
            Divider(
                color = MaterialTheme.colors.primary.copy(0.5F),
                thickness = 0.5.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            SummaryHeading("Questions")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SummaryValues(
                    size = 50,
                    color = Color.Green,
                    value = correctQuestions.toString(),
                    fontSize = 20
                )
                SummaryValues(
                    size = 50,
                    color = Color.Red,
                    value = wrongQuestions.toString(),
                    fontSize = 20
                )
                SummaryValues(
                    size = 50,
                    color = amber,
                    value = skippedQuestions.toString(),
                    fontSize = 20
                )
            }
        }

    }
}

@Composable
fun SummaryHeading(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.padding(vertical = 10.dp),
        fontSize = 15.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Light
    )
}


@Composable
fun SummaryValues(size: Int, value: String, color: Color, fontSize: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(
                color = color,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun PreviousQuizInformation(quizzes: List<Quiz>) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Previous Quizzes",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            quizzes.forEach { quiz ->
                QuizCard(quiz)
            }
        }
    }
}

@Preview(showBackground = true)
@ExperimentalFoundationApi
@Composable
fun PreviewHomePage() {
    QuizCardOverView(20, 12, 34, 20)
}
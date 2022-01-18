package com.example.quiz.view.question_configuration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.quiz.R
import com.example.quiz.data.model.Difficulty
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton
import com.example.quiz.view.composables.CustomDropDownMenu
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun QuestionConfigurationPage(navController: NavController, category: String) {

    val pagerState = rememberPagerState(1)
    var limit by remember {
        mutableStateOf(5F)
    }
    val difficultyList = provideDifficulty()

    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = difficultyList.size,
                itemSpacing = 10.dp,
                state = pagerState,
                modifier = Modifier.padding(20.dp)
            ) { index ->
                DifficultyLevelCard(difficultyList[index])

            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.secondary,
                inactiveColor = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                indicatorShape = CircleShape
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "${limit.toInt()} Questions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = limit,
                onValueChange = { limit = it },
                valueRange = 5F..20F,
                steps = 5,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.secondary,
                    activeTrackColor = MaterialTheme.colors.secondary,
                    inactiveTrackColor = MaterialTheme.colors.onSecondary,
                    activeTickColor = MaterialTheme.colors.onSecondary,
                    inactiveTickColor = MaterialTheme.colors.primary
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(
                heading = "Start",
                onCLick = {
                    navController.navigate(
                        Screen.QuestionsPage.createRoute(
                            category = category,
                            limit = limit.toInt().toString(),
                            difficulty = "medium"
                        )
                    ) {
                        popUpTo(Screen.QuestionsConfigurationPage.route) {
                            inclusive = true
                        }
                    }
                }
            )

        }
    }
}

@Composable
fun DifficultyLevelCard(difficulty: Difficulty) {
    val configuration = LocalConfiguration.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(difficulty.resourceID))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
    )
    Card(
        modifier = Modifier
            .size((configuration.screenWidthDp - 20).dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            LottieAnimation(
                composition = composition,
                progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(20.dp)
            )
            Text(
                text = difficulty.heading,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}


fun provideDifficulty(): List<Difficulty> {
    return listOf(
        Difficulty(heading = "Easy", resourceID = R.raw.easy, value = "easy"),
        Difficulty(heading = "Normal", resourceID = R.raw.normal, value = "medium"),
        Difficulty(heading = "Hard", resourceID = R.raw.hard, value = "hard")
    )
}


@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewQuestionConfigurationPage() {
    QuestionConfigurationPage(navController = rememberNavController(), "")
}
package com.example.quiz.view.question_configuration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun QuestionConfigurationPage(navController: NavController) {

    val pagerState = rememberPagerState(1)
    val items = listOf("5", "10", "15", "20")
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val difficultyList = provideDifficulty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(
            count = difficultyList.size,
            itemSpacing = 10.dp,
            state = pagerState,
            modifier = Modifier.padding(20.dp)

        ) { index ->
            DifficultyLevelCard(difficultyList[index])

        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomDropDownMenu(
            items = items,
            menuExpandedState = expanded,
            selectedIndex = selectedIndex,
            updateMenuExpandStatus = { expanded = !expanded },
            onDismissMenuView = { expanded = false },
            onMenuItemClick = {
                selectedIndex = it
                expanded = false
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        CustomButton(
            heading = "Start",
            onCLick = {
                navController.navigate(Screen.QuestionsPage.route)
            }
        )

    }
}

@Composable
fun DifficultyLevelCard(difficulty: Difficulty) {
    val configuration = LocalConfiguration.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(difficulty.resourceID))
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = true,iterations = LottieConstants.IterateForever,)
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
    QuestionConfigurationPage(navController = rememberNavController())
}
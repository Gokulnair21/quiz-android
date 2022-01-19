package com.example.quiz.view.questions_page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.quiz.data.model.Question
import com.example.quiz.utility.Resource
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton

@Composable
fun QuestionsPage(
    navController: NavController,
    viewModel: QuestionPageViewModel = hiltViewModel(),
    category: String,
    limit: String,
    difficulty: String
) {

    viewModel.getQuestions(
        category = category,
        limit = limit,
        difficulty = difficulty
    )
    val scrollState = rememberScrollState()
    val questions by viewModel.questions.collectAsState()
    val question by viewModel.question.collectAsState()
    val currentQuestion by viewModel.currentQuestion.collectAsState()
    var showAlertDialog by remember {
        mutableStateOf(false)
    }
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        if (showAlertDialog) {
            AlertDialogBox(
                onConfirm = {
                    showAlertDialog = false
                    navController.navigateUp()
                },
                onDismiss = {
                    showAlertDialog = false
                },
                onDismissRequest = {
                    showAlertDialog = false
                }
            )
        }
        when (questions) {
            is Resource.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Getting your questions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        textAlign = TextAlign.Center
                    )
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.secondary,
                    )
                }

            }
            is Resource.Success -> {
                questions.data?.let {
                    Question(
                        scrollState = scrollState,
                        question = question!!,
                        currentQuestionNumber = currentQuestion + 1,
                        totalQuestions = it.size,
                        onClickNext = { answerStatus ->
                            if (currentQuestion + 1 < it.size) {
                                viewModel.nextQuestion(answerStatus)
                            } else {
                                if (answerStatus) {
                                    viewModel.points.value += 1
                                }
                                navController.navigate(
                                    Screen.ResultPage.createRoute(it.size,viewModel.points.value)
                                ) {
                                    popUpTo(Screen.QuestionsPage.route) {
                                        inclusive = true
                                    }
                                }
                            }

                        }
                    )
                }
            }
            is Resource.Error -> {
                Text(text = questions.errorMessage.toString())
            }
        }
    }
    BackHandler() {
        showAlertDialog = true
    }

}


@Composable
fun Question(
    scrollState: ScrollState,
    question: Question,
    totalQuestions: Int,
    currentQuestionNumber: Int,
    onClickNext: (Boolean) -> Unit
) {


    var answerSelected by remember {
        mutableStateOf("")
    }
    var isAnswerCorrect by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        ProgressIndicator(
            totalQuestions = totalQuestions,
            currentQuestionNumber = currentQuestionNumber
        )
        Text(
            text = question.question,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Card(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                question.answers.apply {
                    QuestionOption(answerA, answerSelected == answerA) {
                        answerSelected = answerA
                        isAnswerCorrect = question.correctAnswers.answerACorrect.toBoolean()
                    }
                    QuestionOption(answerB, answerSelected == answerB) {
                        answerSelected = answerB
                        isAnswerCorrect = question.correctAnswers.answerBCorrect.toBoolean()
                    }
                    answerC?.let { value ->
                        QuestionOption(value, answerSelected == answerC) {
                            answerSelected = answerC
                            isAnswerCorrect = question.correctAnswers.answerCCorrect.toBoolean()
                        }
                    }
                    answerD?.let { value ->
                        QuestionOption(value, answerSelected == answerD) {
                            answerSelected = answerD
                            isAnswerCorrect = question.correctAnswers.answerDCorrect.toBoolean()
                        }
                    }
                    answerE?.let { value ->
                        QuestionOption(value, answerSelected == answerE) {
                            answerSelected = answerE
                            isAnswerCorrect = question.correctAnswers.answerECorrect.toBoolean()
                        }
                    }
                    answerF?.let { value ->
                        QuestionOption(value, answerSelected == answerF) {
                            answerSelected = answerF
                            isAnswerCorrect = question.correctAnswers.answerFCorrect.toBoolean()
                        }
                    }
                }


            }

        }
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            CustomButton(
                heading = "Next",
                onCLick = { onClickNext(isAnswerCorrect) }
            )
        }
    }
}


@Composable
fun AlertDialogBox(onDismissRequest: () -> Unit, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        shape = RoundedCornerShape(20.dp),
        title = {
            Text(text = "Alert")
        },
        text = {
            Text(
                text = "Are you sure you want to exit form the game ?",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm", fontSize = 15.sp)
            }

        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss", fontSize = 15.sp)
            }
        }
    )
}

@Composable
fun ProgressIndicator(totalQuestions: Int, currentQuestionNumber: Int) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            LinearProgressIndicator(
                color = MaterialTheme.colors.secondary,
                progress = (currentQuestionNumber.toDouble() / totalQuestions).toFloat(),
                backgroundColor = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .weight(1F)
                    .height(10.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.crown),
                contentDescription = "Crown",
                modifier = Modifier
                    .size(30.dp)
                    .padding(horizontal = 5.dp)
            )
            Text(
                text = "${currentQuestionNumber}/${totalQuestions}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun QuestionOption(answer: String, isSelected: Boolean, onCLick: () -> Unit) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary.copy(
                0.1F
            ),
            contentColor = if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.primary,
        ),
        shape = RoundedCornerShape(5.dp),
        onClick = onCLick,
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        ),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Text(
            text = answer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
        )
    }
}


@Preview
@Composable
fun PreviewQuestionPage() {
    AlertDialogBox(
        onConfirm = {},
        onDismiss = {},
        onDismissRequest = {}
    )
}
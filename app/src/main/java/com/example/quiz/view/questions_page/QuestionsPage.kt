package com.example.quiz.view.questions_page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quiz.R
import com.example.quiz.view.composables.CustomButton

@Composable
fun QuestionsPage(navController: NavController) {
    val scrollState = rememberScrollState()
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            ProgressIndicator()
            Text(
                text = "What is the best programming language?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 27.sp,
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

                    QuestionOption()
                    QuestionOption()
                    QuestionOption()
                    QuestionOption()
                }

            }
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                CustomButton(
                    heading = "Next",
                    onCLick = {

                    }
                )
            }
        }
    }

}


@Composable
fun ProgressIndicator() {
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
                progress = 0.5F,
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
                text = "0/10",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun QuestionOption() {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.primary.copy(0.1F),
            contentColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(5.dp),
        onClick = { },
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Option A",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 5.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
        )
    }
}


@Preview
@Composable
fun PreviewQuestionPage() {
    QuestionsPage(navController = rememberNavController())
}
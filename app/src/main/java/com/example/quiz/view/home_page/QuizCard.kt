package com.example.quiz.view.home_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.R
import com.example.quiz.data.model.Quiz
import com.example.quiz.ui.theme.amber
import com.example.quiz.utility.Constants
import com.example.quiz.utility.getImageResource
import com.example.quiz.utility.toCapitalize


@Composable
fun QuizCard(quiz: Quiz) {
    Card(
        border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colors.primary.copy(0.5F)),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Card(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1F),
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = Constants.categories.getImageResource(quiz.category)),
                    contentDescription = quiz.category.toCapitalize(),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(text = "${quiz.totalQuestions} Questions", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row() {
                    Card(
                        backgroundColor = Color.Green.copy(0.1F),
                        shape = CircleShape,
                        elevation = 0.dp,
                        border = BorderStroke(width = 0.5.dp, color = Color.Green)
                    ) {
                        Text(
                            text = quiz.correctQuestions.toString(),
                            color = Color.Green,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        backgroundColor = Color.Red.copy(0.1F),
                        shape = CircleShape,
                        elevation = 0.dp,
                        border = BorderStroke(width = 0.5.dp, color = Color.Red)
                    ) {
                        Text(
                            text = quiz.wrongQuestions.toString(),
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        backgroundColor = amber.copy(0.1F),
                        shape = CircleShape,
                        elevation = 0.dp,
                        border = BorderStroke(width = 0.5.dp, color = amber)
                    ) {
                        Text(
                            text = quiz.skippedQuestions.toString(),
                            color = amber,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }

            }
            Card(
                backgroundColor = getDifficultyColor(quiz.difficulty), modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
            ) {
            }
        }
    }
}


fun getDifficultyColor(level: String): Color {
    return when (level) {
        "hard" -> {
            Color.Red
        }
        "medium" -> {
            amber
        }
        else -> {
            Color.Green
        }
    }

}


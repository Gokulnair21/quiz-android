package com.example.quiz.view.questions_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
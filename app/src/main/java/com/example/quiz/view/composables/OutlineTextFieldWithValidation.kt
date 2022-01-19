package com.example.quiz.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlineTextFieldWithValidation(
    leadingIcon: @Composable () -> Unit,
    showError: Boolean,
    error: String,
    modifier: Modifier,
    value: String,
    onChange: (String) -> Unit,
    textStyle: TextStyle,
    colors: TextFieldColors


) {
    Column(modifier = modifier) {
        OutlinedTextField(
            isError = showError,
            leadingIcon = leadingIcon,
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = textStyle,
            colors = colors
        )
        if (showError) {
            Text(
                text = error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 5.dp),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 12.sp
            )
        }
    }
}
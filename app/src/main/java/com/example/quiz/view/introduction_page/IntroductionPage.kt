package com.example.quiz.view.introduction_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quiz.R
import com.example.quiz.utility.Screen


@Composable
fun IntroductionPage(navController: NavController) {
    var name by remember {
        mutableStateOf("")
    }
    Surface(color = MaterialTheme.colors.primary) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quiz),
                    contentDescription = "Quiz",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(bottom = 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = "Let's Play Quiz,", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(text = "Enter your information below", fontSize = 15.sp)
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 50.dp),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        focusedBorderColor = MaterialTheme.colors.secondary,
                        unfocusedBorderColor = MaterialTheme.colors.secondary
                    ),
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSecondary
                    ), onClick = { navController.navigate(Screen.HomePage.route) }) {
                    Text(
                        text = "Lets play",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
            }

        }
    }

}


@Preview
@Composable
fun IntroductionPagePreview() {
    IntroductionPage(navController = rememberNavController())
}
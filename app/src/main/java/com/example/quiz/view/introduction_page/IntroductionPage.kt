package com.example.quiz.view.introduction_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz.R
import com.example.quiz.utility.Screen
import com.example.quiz.view.composables.CustomButton
import com.example.quiz.view.composables.OutlineTextFieldWithValidation
import com.example.quiz.view.home_page.HomePageViewModel
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun IntroductionPage(
    navController: NavController,
    introductionPageViewModel: IntroductionPageViewModel = hiltViewModel(),
    homePageViewModel: HomePageViewModel
) {
    val name by introductionPageViewModel.name.collectAsState()

    val showError by introductionPageViewModel.showError.collectAsState()

    val configuration = LocalConfiguration.current
    Surface(color = MaterialTheme.colors.primary) {
        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quiz),
                    contentDescription = "Quiz",
                    modifier = Modifier
                        .size((configuration.screenWidthDp - 20).dp)
                        .padding(bottom = 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = "Let's Play Quiz,", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(text = "Enter your information below", fontSize = 15.sp)
                OutlineTextFieldWithValidation(
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Person",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    },
                    value = name,
                    onChange = {
                        introductionPageViewModel.getValueForName(it)
                    },
                    showError = showError,
                    error = "Please enter your name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 50.dp),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        backgroundColor = MaterialTheme.colors.primary,
                        focusedBorderColor = MaterialTheme.colors.secondary,
                        unfocusedBorderColor = MaterialTheme.colors.secondary,
                        errorBorderColor = MaterialTheme.colors.onPrimary
                    ),
                )
                CustomButton(heading = "Lets Play") {
                    if (introductionPageViewModel.onCLickLetsPlay()) {
                        homePageViewModel.getName(name)
                        navController.navigate(Screen.HomePage.route) {
                            popUpTo(Screen.IntroductionPage.route) {
                                inclusive = true
                            }
                        }

                    }
                }
            }

        }
    }

}


@Preview
@Composable
fun IntroductionPagePreview() {
}
package com.example.quiz.view.home_page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quiz.R
import com.example.quiz.data.model.Category
import com.example.quiz.utility.Screen


@ExperimentalFoundationApi
@Composable
fun HomePage(navController: NavController) {
    Surface(color = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

        }
    }
}



@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    HomePage(navController = rememberNavController())
}
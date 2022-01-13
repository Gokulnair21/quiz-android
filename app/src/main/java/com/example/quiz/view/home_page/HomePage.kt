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
    val categoryList = createCategoryList()
    Surface(color = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Choose Category",
                fontWeight = FontWeight.Normal,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 5.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
                backgroundColor = MaterialTheme.colors.background,
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(categoryList.size) { index ->
                        CategoryCard(category = categoryList[index]) {
                            navController.navigate(Screen.QuestionsConfigurationPage.route)
                        }
                    }
                }
            }


        }
    }
}


fun createCategoryList(): List<Category> {
    return listOf(
        Category(
            heading = "Linux",
            value = "linux",
            totalQuestions = 99,
            imageID = R.drawable.linux
        ),
        Category(
            heading = "Bash",
            value = "bash",
            totalQuestions = 77,
            imageID = R.drawable.bash
        ),
        Category(
            heading = "HTML",
            value = "html",
            totalQuestions = 139,
            imageID = R.drawable.html
        ),
        Category(
            heading = "Javascript",
            value = "javascript",
            totalQuestions = 25,
            imageID = R.drawable.javascript
        ),
        Category(
            heading = "Laravel",
            value = "laravel",
            totalQuestions = 10,
            imageID = R.drawable.laravel
        ),
        Category(
            heading = "Kubernetes",
            value = "kubernetes",
            totalQuestions = 136,
            imageID = R.drawable.kubernetes
        ),
        Category(
            heading = "MySQL",
            value = "mysql",
            totalQuestions = 153,
            imageID = R.drawable.mysql
        ),
        Category(
            heading = "PHP",
            value = "php",
            totalQuestions = 181,
            imageID = R.drawable.php
        ),
        Category(
            heading = "Wordpress",
            value = "wordpress",
            totalQuestions = 66,
            imageID = R.drawable.wordpress
        ),
        Category(
            heading = "DevOps",
            value = "devops",
            totalQuestions = 23,
            imageID = R.drawable.devops
        ),
        Category(
            heading = "Docker",
            value = "docker",
            totalQuestions = 125,
            imageID = R.drawable.docker
        ),
        Category(
            heading = "Random",
            value = "",
            totalQuestions = 0,
            imageID = R.drawable.random
        ),
    )
}


@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1F),
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick.invoke() }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)
        ) {
            Card(elevation = 0.dp, shape = RoundedCornerShape(10.dp)) {
                Image(
                    painter = painterResource(id = category.imageID),
                    contentDescription = category.heading,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Text(
                text = category.heading,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "${category.totalQuestions} Questions",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    HomePage(navController = rememberNavController())
}
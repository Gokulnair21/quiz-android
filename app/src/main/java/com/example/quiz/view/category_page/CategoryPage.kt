package com.example.quiz.view.category_page


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
import com.example.quiz.utility.Constants
import com.example.quiz.utility.Screen


@ExperimentalFoundationApi
@Composable
fun CategoryPage(navController: NavController) {
    val categoryList = Constants.categories
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
                            navController.navigate(
                                Screen.QuestionsConfigurationPage.createRoute(
                                    categoryList[index].value
                                )
                            )
                        }
                    }
                }
            }
        }
    }
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
fun PreviewCategoryPage() {
    CategoryPage(navController = rememberNavController())
}
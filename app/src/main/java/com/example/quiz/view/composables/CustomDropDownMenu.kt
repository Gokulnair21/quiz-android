package com.example.quiz.view.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.rememberPagerState

@Composable
fun CustomDropDownMenu(
    items: List<String>,
    menuExpandedState: Boolean,
    selectedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.TopStart)
            .padding(top = 10.dp)
            .border(width = 2.dp, color = MaterialTheme.colors.secondary)
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                }
            )

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${items[selectedIndex]} Questions",
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondary
            )
            Icon(
                if (menuExpandedState) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Arrow",
                modifier = Modifier.padding(10.dp),
                tint = MaterialTheme.colors.secondary
            )
        }
        DropdownMenu(
            modifier=Modifier.fillMaxWidth(),
            expanded = menuExpandedState,
            onDismissRequest = { onDismissMenuView() }) {
            items.forEachIndexed { index, title ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onMenuItemClick(index)

                    }) {
                    Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDropDown() {
    val items = listOf("5", "10", "15", "20")
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    CustomDropDownMenu(
        items = items,
        menuExpandedState = expanded,
        selectedIndex = selectedIndex,
        updateMenuExpandStatus = { expanded = !expanded },
        onDismissMenuView = { expanded = false },
        onMenuItemClick = {
            selectedIndex = it
            expanded = false
        }

    )
}
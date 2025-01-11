package com.example.notes.ui.home

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onAddClick: () -> Unit = {}) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddClick()
                }
            ) {
                Text(text = "Add")
            }
        }
    ) {

    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}

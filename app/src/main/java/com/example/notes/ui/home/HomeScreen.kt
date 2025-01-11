package com.example.notes.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notes.R
import com.example.notes.ui.components.notes.NoteList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNoteClick: (id: Int?) -> Unit = {},
    noteSharedViewModel: NoteSharedViewModel = hiltViewModel()
) {
    val noteList by noteSharedViewModel.noteListState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNoteClick(null)
                }
            ) {
                Text(text = "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (noteList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.message_no_notes))
                }
            } else {
                NoteList(
                    noteList = noteList,
                    onNoteClick = { id ->
                        onNoteClick(id)
                    },
                    onNoteDeleteButtonClick = { id ->
                        noteSharedViewModel.deleteNote(id = id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}

package com.example.notes.ui.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notes.R
import com.example.notes.data.pojo.Note
import com.example.notes.ui.home.NoteSharedViewModel

@Composable
fun NoteDetailsScreen(
    noteSharedViewModel: NoteSharedViewModel = hiltViewModel(),
    noteDetailsViewModel: NoteDetailsViewModel = hiltViewModel(),
    id: Int? = null,
    onNoteSaved: () -> Unit = {}
) {
    val uiState by noteDetailsViewModel.uiState.collectAsStateWithLifecycle()

    val note = noteSharedViewModel.getNote(id)

    LaunchedEffect(note) {
        note?.let {
            noteDetailsViewModel.onTitleChange(note.title)
            noteDetailsViewModel.onDescriptionChange(note.description)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.title,
                onValueChange = { title ->
                    noteDetailsViewModel.onTitleChange(title)
                },
                placeholder = { Text(text = stringResource(R.string.placeholder_title)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = uiState.description,
                onValueChange = { description ->
                    noteDetailsViewModel.onDescriptionChange(description)
                },
                placeholder = { Text(text = stringResource(R.string.placeholder_description)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.isDataEntered,
                onClick = {
                    noteSharedViewModel.addEditNote(
                        id = id,
                        note = Note(
                            title = uiState.title.trim(),
                            description = uiState.description.trim()
                        )
                    )
                    onNoteSaved()
                }
            ) {
                Text(text = stringResource(R.string.button_save))
            }
        }
    }
}

@Preview
@Composable
private fun AddEditNoteScreenPrev() {
    NoteDetailsScreen()
}

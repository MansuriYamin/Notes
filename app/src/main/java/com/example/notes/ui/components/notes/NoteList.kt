package com.example.notes.ui.components.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.data.pojo.Note

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    noteList: List<Note>,
    onNoteClick: (id: Int) -> Unit = {},
    onNoteDeleteButtonClick: (id: Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
    ) {
        items(noteList) { note ->
            NoteCard(
                note = note,
                onNoteClick = onNoteClick,
                onNoteDeleteButtonClick = onNoteDeleteButtonClick
            )
        }
    }
}

@Preview
@Composable
private fun NoteListPrev() {
    NoteList(noteList = listOf(Note(title = "Title", description = "Description")))
}

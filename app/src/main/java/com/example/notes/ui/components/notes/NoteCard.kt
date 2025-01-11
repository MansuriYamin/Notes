package com.example.notes.ui.components.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.R
import com.example.notes.data.pojo.Note

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (id: Int) -> Unit = {},
    onNoteDeleteButtonClick: (id: Int) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onNoteClick(note.id)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = note.title,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = note.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }

            IconButton(
                onClick = {
                    onNoteDeleteButtonClick(note.id)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(R.string.content_description_delete_note)
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteCardPrev(modifier: Modifier = Modifier) {
    NoteCard(note = Note(title = "Title", description = "Description"))
}

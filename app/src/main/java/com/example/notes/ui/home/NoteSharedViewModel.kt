package com.example.notes.ui.home

import androidx.lifecycle.ViewModel
import com.example.notes.data.pojo.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoteSharedViewModel @Inject constructor() : ViewModel() {

    private val _noteListState = MutableStateFlow<List<Note>>(emptyList())
    val noteListState = _noteListState.asStateFlow()

    fun addEditNote(id: Int?, note: Note) {
        // If id is not null that means the note already exist then edit it, otherwise add a new note.
        id?.let {
            editNote(id = id, noteToUpdate = note)
        } ?: run {
            addNote(note = note)
        }
    }

    private fun addNote(note: Note) {
        _noteListState.update { noteList ->
            listOf(note.copy(id = noteList.size)) + noteList // Add note on top by assigning id
        }
    }

    private fun editNote(id: Int, noteToUpdate: Note) {
        _noteListState.update { noteList ->
            noteList.map { note ->
                // Find specific note to edit, and the update it's content.
                if (id == note.id) {
                    note.copy(
                        title = noteToUpdate.title,
                        description = noteToUpdate.description
                    )
                } else {
                    note
                }
            }
        }
    }

    fun deleteNote(id: Int) {
        // Find note by id and then remove it from the list
        getNote(id)?.let { noteToDelete ->
            _noteListState.update { noteList ->
                noteList - noteToDelete
            }
        }
    }

    fun getNote(id: Int?): Note? {
        return _noteListState.value.find { note -> note.id == id }
    }
}
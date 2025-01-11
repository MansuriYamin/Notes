package com.example.notes.ui.notes

data class NoteDetailsUiState(
    val title: String = "",
    val description: String = ""
) {

    val isDataEntered: Boolean = title.isNotBlank() && description.isNotBlank()
}
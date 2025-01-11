package com.example.notes.ui.notes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(NoteDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(title: String) {
        _uiState.update { state ->
            state.copy(title = title)
        }
    }

    fun onDescriptionChange(description: String) {
        _uiState.update { state ->
            state.copy(description = description)
        }
    }
}
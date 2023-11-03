package com.compose.jetbooks.ui.theme.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jetbooks.data.BooksRepository
import com.compose.jetbooks.model.BookList
import com.compose.jetbooks.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<BookList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<BookList>>
        get() = _uiState

    fun getBooksById(bookId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(booksRepository.getBookById(bookId))
        }
    }
}


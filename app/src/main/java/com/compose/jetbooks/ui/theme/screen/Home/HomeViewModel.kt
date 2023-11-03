package com.compose.jetbooks.ui.theme.screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jetbooks.data.BooksRepository
import com.compose.jetbooks.model.BookList
import com.compose.jetbooks.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<BookList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<BookList>>>
        get() = _uiState

    fun getAllBooks() {
        viewModelScope.launch {
            booksRepository.getAllBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { dataBook ->
                    _uiState.value = UiState.Success(dataBook)
                }
        }
    }
}
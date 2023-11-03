package com.compose.jetbooks.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.jetbooks.data.BooksRepository
import com.compose.jetbooks.ui.theme.screen.Home.HomeViewModel
import com.compose.jetbooks.ui.theme.screen.detail.DetailViewModel


class ViewModelFactory(private val booksRepository: BooksRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(booksRepository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
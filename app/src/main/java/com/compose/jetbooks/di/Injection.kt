package com.compose.jetbooks.di

import com.compose.jetbooks.data.BooksRepository

object Injection {
    fun provideBooksRepository(): BooksRepository {
        return BooksRepository().getIntance()
    }
}
package com.compose.jetbooks.data

import com.compose.jetbooks.model.BookList
import com.compose.jetbooks.model.DummyBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BooksRepository {
    private val dataBooks = mutableListOf<BookList>()

    init {
        if (dataBooks.isEmpty()) {
            DummyBook.books.forEach {
                dataBooks.add(BookList(it, 0))
            }
        }
    }

    fun getBookById(BookId: Long): BookList {
        return dataBooks.first {
            it.book.id == BookId
        }
    }

    fun getAllBooks(): Flow<List<BookList>> {
        return flowOf(dataBooks)
    }

    private var instance: BooksRepository? = null

    fun getIntance(): BooksRepository =
        instance ?: synchronized(this) {
            BooksRepository().apply {
                instance = this
            }
        }
}

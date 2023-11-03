package com.compose.jetbooks.model

data class Book (
    val id: Long,
    val author: String,
    val logo: Int,
    val type: String,
    val desc: String
)
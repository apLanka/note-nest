package com.example.note_nest

data class Note(
    val id: String,
    val title: String,
    val moduleCode: String,
    val moduleName: String,
    val description: String,
    val createdDate: String,
    val filePath: String? = null,
    val comments: List<Comment> = emptyList()
)

data class Comment(
    val id: String,
    val userName: String,
    val userAvatar: String,
    val message: String,
    val timestamp: String
)
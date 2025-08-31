package com.example.note_nest

data class Group(
    val id: String,
    val name: String,
    val description: String,
    val memberCount: Int,
    val members: List<Member> = emptyList(),
    val sharedNotes: List<Note> = emptyList(),
    val coverImage: String? = null,
    val createdDate: String
)

data class Member(
    val id: String,
    val name: String,
    val avatar: String? = null,
    val email: String? = null
)
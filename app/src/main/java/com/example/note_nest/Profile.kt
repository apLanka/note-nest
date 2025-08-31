package com.example.note_nest

data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String? = null,
    val stats: UserStats,
    val achievements: List<Achievement>
)

data class UserStats(
    val notesShared: Int,
    val downloads: Int,
    val studyStreak: Int // in days
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val iconResource: Int,
    val isCompleted: Boolean,
    val progress: Int = 100, // percentage if completed
    val backgroundColor: String = "#B08CFF"
)

data class SettingsItem(
    val id: String,
    val title: String,
    val iconResource: Int,
    val hasArrow: Boolean = true
)
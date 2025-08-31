package com.example.note_nest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var profileAvatar: ImageView
    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var notesSharedCount: TextView
    private lateinit var downloadsCount: TextView
    private lateinit var studyStreakCount: TextView
    private lateinit var achievementsRecyclerView: RecyclerView
    private lateinit var settingsRecyclerView: RecyclerView
    private lateinit var achievementsAdapter: AchievementsAdapter
    private lateinit var settingsAdapter: SettingsAdapter

    private var currentProfile: UserProfile? = null
    private var achievementsList = mutableListOf<Achievement>()
    private var settingsList = mutableListOf<SettingsItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerViews()
        loadProfileData()
    }

    private fun initViews(view: View) {
        profileAvatar = view.findViewById(R.id.profileAvatar)
        profileName = view.findViewById(R.id.profileName)
        profileEmail = view.findViewById(R.id.profileEmail)
        notesSharedCount = view.findViewById(R.id.notesSharedCount)
        downloadsCount = view.findViewById(R.id.downloadsCount)
        studyStreakCount = view.findViewById(R.id.studyStreakCount)
        achievementsRecyclerView = view.findViewById(R.id.recyclerViewAchievements)
        settingsRecyclerView = view.findViewById(R.id.recyclerViewSettings)
    }

    private fun setupRecyclerViews() {
        // Achievements RecyclerView
        achievementsAdapter = AchievementsAdapter(achievementsList)
        achievementsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        achievementsRecyclerView.adapter = achievementsAdapter

        // Settings RecyclerView
        settingsAdapter = SettingsAdapter(settingsList) { settingsItem ->
            handleSettingsClick(settingsItem)
        }
        settingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        settingsRecyclerView.adapter = settingsAdapter
    }

    private fun loadProfileData() {
        // Sample profile data - in real app this would come from database/API
        currentProfile = UserProfile(
            id = "1",
            name = "Pasindu Lanka",
            email = "pasindulankaa@gmail.com",
            stats = UserStats(
                notesShared = 32,
                downloads = 158,
                studyStreak = 7
            ),
            achievements = listOf(
                Achievement(
                    id = "1",
                    title = "Note Master",
                    description = "Created 25+ notes",
                    iconResource = R.drawable.ic_trophy,
                    isCompleted = true
                ),
                Achievement(
                    id = "2",
                    title = "Collaborator",
                    description = "Shared notes with 10+ people",
                    iconResource = R.drawable.ic_collaborator,
                    isCompleted = true
                ),
                Achievement(
                    id = "3",
                    title = "Study Guru",
                    description = "Maintained a 14-day study streak",
                    iconResource = R.drawable.ic_study_guru,
                    isCompleted = false,
                    progress = 50
                ),
                Achievement(
                    id = "4",
                    title = "Knowledge Explorer",
                    description = "Created notes in 5+ subjects",
                    iconResource = R.drawable.ic_knowledge_explorer,
                    isCompleted = true
                )
            )
        )

        // Display profile data
        currentProfile?.let { profile ->
            profileName.text = profile.name
            profileEmail.text = profile.email
            profileAvatar.setImageResource(R.drawable.logo)
            
            // Display stats
            notesSharedCount.text = profile.stats.notesShared.toString()
            downloadsCount.text = profile.stats.downloads.toString()
            studyStreakCount.text = "${profile.stats.studyStreak} days"
            
            // Load achievements
            achievementsList.clear()
            achievementsList.addAll(profile.achievements)
            achievementsAdapter.notifyDataSetChanged()
        }

        // Load settings items
        settingsList.clear()
        settingsList.addAll(
            listOf(
                SettingsItem("account", "Account Settings", R.drawable.ic_account),
                SettingsItem("notifications", "Notifications", R.drawable.ic_notifications),
                SettingsItem("privacy", "Privacy", R.drawable.ic_privacy),
                SettingsItem("help", "Help & Support", R.drawable.ic_help),
                SettingsItem("about", "About NoteNest", R.drawable.ic_about)
            )
        )
        settingsAdapter.notifyDataSetChanged()
    }

    private fun handleSettingsClick(settingsItem: SettingsItem) {
        when (settingsItem.id) {
            "account" -> {
                Toast.makeText(requireContext(), "Account Settings", Toast.LENGTH_SHORT).show()
            }
            "notifications" -> {
                Toast.makeText(requireContext(), "Notifications", Toast.LENGTH_SHORT).show()
            }
            "privacy" -> {
                Toast.makeText(requireContext(), "Privacy", Toast.LENGTH_SHORT).show()
            }
            "help" -> {
                Toast.makeText(requireContext(), "Help & Support", Toast.LENGTH_SHORT).show()
            }
            "about" -> {
                Toast.makeText(requireContext(), "About NoteNest", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
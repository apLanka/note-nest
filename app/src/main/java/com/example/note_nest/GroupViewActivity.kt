package com.example.note_nest

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroupViewActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var groupImage: ImageView
    private lateinit var groupTitle: TextView
    private lateinit var membersRecyclerView: RecyclerView
    private lateinit var sharedNotesRecyclerView: RecyclerView
    private lateinit var membersAdapter: MembersAdapter
    private lateinit var sharedNotesAdapter: NotesAdapter

    private var currentGroup: Group? = null
    private var membersList = mutableListOf<Member>()
    private var sharedNotesList = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_view)

        initViews()
        setupRecyclerViews()
        loadGroupData()
        setupListeners()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        profileImage = findViewById(R.id.profileImage)
        groupImage = findViewById(R.id.groupImage)
        groupTitle = findViewById(R.id.textGroupTitle)
        membersRecyclerView = findViewById(R.id.recyclerViewMembers)
        sharedNotesRecyclerView = findViewById(R.id.recyclerViewSharedNotes)
    }

    private fun setupRecyclerViews() {
        // Members RecyclerView (Horizontal)
        membersAdapter = MembersAdapter(membersList)
        membersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        membersRecyclerView.adapter = membersAdapter

        // Shared Notes RecyclerView (Vertical)
        sharedNotesAdapter = NotesAdapter(sharedNotesList) { note ->
            // Navigate to note details - reuse existing NoteViewActivity
            val intent = android.content.Intent(this, NoteViewActivity::class.java)
            intent.putExtra("note_id", note.id)
            startActivity(intent)
        }
        sharedNotesRecyclerView.layoutManager = LinearLayoutManager(this)
        sharedNotesRecyclerView.adapter = sharedNotesAdapter
    }

    private fun loadGroupData() {
        val groupId = intent.getStringExtra("group_id")
        
        // Sample data - in real app this would come from database/API
        currentGroup = Group(
            id = groupId ?: "1",
            name = "Study Buddies",
            description = "A collaborative study group for sharing notes and resources",
            memberCount = 5,
            members = listOf(
                Member("1", "John Doe"),
                Member("2", "Jane Smith"),
                Member("3", "Mike Johnson"),
                Member("4", "Sarah Wilson"),
                Member("5", "Alex Brown")
            ),
            sharedNotes = listOf(
                Note(
                    id = "1",
                    title = "DSA Lec - 2",
                    moduleCode = "IT2070",
                    moduleName = "Data Structures and Algorithms",
                    description = "Binary Trees and Tree Traversal",
                    createdDate = "Today"
                ),
                Note(
                    id = "2",
                    title = "DSA Lec - 2",
                    moduleCode = "IT2070",
                    moduleName = "Data Structures and Algorithms",
                    description = "Advanced sorting algorithms",
                    createdDate = "Today"
                )
            ),
            createdDate = "2024-01-15"
        )

        // Display group data
        currentGroup?.let { group ->
            groupTitle.text = group.name
            groupImage.setImageResource(R.drawable.group_img)
            
            // Load members
            membersList.clear()
            membersList.addAll(group.members)
            membersAdapter.notifyDataSetChanged()
            
            // Load shared notes
            sharedNotesList.clear()
            sharedNotesList.addAll(group.sharedNotes)
            sharedNotesAdapter.notifyDataSetChanged()
        }
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            finish()
        }
    }
}
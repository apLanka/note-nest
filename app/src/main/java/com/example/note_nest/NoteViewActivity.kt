package com.example.note_nest

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteViewActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var titleText: TextView
    private lateinit var dateText: TextView
    private lateinit var moduleCodeText: TextView
    private lateinit var contentText: TextView
    private lateinit var downloadButton: LinearLayout
    private lateinit var commentButton: LinearLayout
    private lateinit var shareButton: LinearLayout
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var commentsAdapter: CommentsAdapter

    private var currentNote: Note? = null
    private var commentsList = mutableListOf<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_view)

        initViews()
        setupCommentsRecyclerView()
        loadNoteData()
        setupListeners()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        profileImage = findViewById(R.id.profileImage)
        titleText = findViewById(R.id.textNoteTitle)
        dateText = findViewById(R.id.textNoteDate)
        moduleCodeText = findViewById(R.id.textModuleCode)
        contentText = findViewById(R.id.textNoteContent)
        downloadButton = findViewById(R.id.buttonDownload)
        commentButton = findViewById(R.id.buttonComment)
        shareButton = findViewById(R.id.buttonShare)
        commentsRecyclerView = findViewById(R.id.recyclerViewComments)
    }

    private fun loadNoteData() {
        val noteId = intent.getStringExtra("note_id")
        
        // Sample data - in real app this would come from database/API
        currentNote = when (noteId) {
            "1" -> Note(
                id = "1",
                title = "DSA Assignment",
                moduleCode = "IT2070",
                moduleName = "Data Structures and Algorithms",
                description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and",
                createdDate = "Today"
            )
            else -> Note(
                id = noteId ?: "unknown",
                title = "Sample Note",
                moduleCode = "IT2070",
                moduleName = "Sample Module",
                description = "Sample content",
                createdDate = "Today"
            )
        }

        // Display note data
        currentNote?.let { note ->
            titleText.text = note.title
            dateText.text = note.createdDate
            moduleCodeText.text = note.moduleCode
            contentText.text = note.description
        }

        loadComments()
    }

    private fun loadComments() {
        commentsList.clear()
        commentsList.addAll(
            listOf(
                Comment(
                    id = "1",
                    userName = "Ethan Cater",
                    userAvatar = "",
                    message = "These nots are incredibly helpful.. Thanks for sharing",
                    timestamp = "2d"
                ),
                Comment(
                    id = "2",
                    userName = "Ethan Cater",
                    userAvatar = "",
                    message = "These nots are incredibly helpful.. Thanks for sharing",
                    timestamp = "2d"
                ),
                Comment(
                    id = "3",
                    userName = "Ethan Cater",
                    userAvatar = "",
                    message = "These nots are incredibly helpful.. Thanks for sharing",
                    timestamp = "2d"
                )
            )
        )
        commentsAdapter.notifyDataSetChanged()
    }

    private fun setupCommentsRecyclerView() {
        commentsAdapter = CommentsAdapter(commentsList)
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        commentsRecyclerView.adapter = commentsAdapter
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            finish()
        }

        downloadButton.setOnClickListener {
            Toast.makeText(this, "Download feature coming soon", Toast.LENGTH_SHORT).show()
        }

        commentButton.setOnClickListener {
            Toast.makeText(this, "Comment feature coming soon", Toast.LENGTH_SHORT).show()
        }

        shareButton.setOnClickListener {
            Toast.makeText(this, "Share feature coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.note_nest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class GroupCreateActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var groupNameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var inviteMembersEditText: EditText
    private lateinit var coverImageCard: CardView
    private lateinit var selectFilesButton: Button
    private lateinit var selectedImageText: TextView
    private lateinit var createGroupButton: Button

    private var selectedImageUri: Uri? = null

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            selectedImageUri?.let { uri ->
                val fileName = getFileName(uri)
                selectedImageText.text = fileName ?: "Selected image"
                selectedImageText.visibility = TextView.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_create)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        profileImage = findViewById(R.id.profileImage)
        groupNameEditText = findViewById(R.id.editTextGroupName)
        descriptionEditText = findViewById(R.id.editTextDescription)
        inviteMembersEditText = findViewById(R.id.editTextInviteMembers)
        coverImageCard = findViewById(R.id.coverImageCard)
        selectFilesButton = findViewById(R.id.buttonSelectFiles)
        selectedImageText = findViewById(R.id.textSelectedImage)
        createGroupButton = findViewById(R.id.buttonCreateGroup)
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            finish()
        }

        selectFilesButton.setOnClickListener {
            openImagePicker()
        }

        coverImageCard.setOnClickListener {
            openImagePicker()
        }

        createGroupButton.setOnClickListener {
            createGroup()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        imagePickerLauncher.launch(intent)
    }

    private fun createGroup() {
        val groupName = groupNameEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val inviteMembers = inviteMembersEditText.text.toString().trim()

        if (groupName.isEmpty()) {
            groupNameEditText.error = "Group name is required"
            return
        }

        if (description.isEmpty()) {
            descriptionEditText.error = "Description is required"
            return
        }

        // In a real app, you would save the group to database/API here
        val group = Group(
            id = System.currentTimeMillis().toString(),
            name = groupName,
            description = description,
            memberCount = 1, // Creator is the first member
            coverImage = selectedImageUri?.toString(),
            createdDate = "Today"
        )

        Toast.makeText(this, "Group created successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    val displayNameIndex = cursor.getColumnIndex("_display_name")
                    if (displayNameIndex != -1) {
                        result = cursor.getString(displayNameIndex)
                    }
                }
            } finally {
                cursor?.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/')
            if (cut != -1) {
                result = result?.substring(cut!! + 1)
            }
        }
        return result
    }
}
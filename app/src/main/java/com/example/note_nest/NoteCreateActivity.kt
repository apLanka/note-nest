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

class NoteCreateActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var titleEditText: EditText
    private lateinit var moduleNameEditText: EditText
    private lateinit var moduleCodeEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var fileUploadCard: CardView
    private lateinit var selectFilesButton: Button
    private lateinit var selectedFilesText: TextView
    private lateinit var uploadButton: Button

    private var selectedFileUri: Uri? = null

    private val filePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedFileUri = result.data?.data
            selectedFileUri?.let { uri ->
                val fileName = getFileName(uri)
                selectedFilesText.text = fileName ?: "Selected file"
                selectedFilesText.visibility = TextView.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_create)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        backButton = findViewById(R.id.backButton)
        profileImage = findViewById(R.id.profileImage)
        titleEditText = findViewById(R.id.editTextTitle)
        moduleNameEditText = findViewById(R.id.editTextModuleName)
        moduleCodeEditText = findViewById(R.id.editTextModuleCode)
        descriptionEditText = findViewById(R.id.editTextDescription)
        fileUploadCard = findViewById(R.id.fileUploadCard)
        selectFilesButton = findViewById(R.id.buttonSelectFiles)
        selectedFilesText = findViewById(R.id.textSelectedFiles)
        uploadButton = findViewById(R.id.buttonUpload)
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            finish()
        }

        selectFilesButton.setOnClickListener {
            openFilePicker()
        }

        fileUploadCard.setOnClickListener {
            openFilePicker()
        }

        uploadButton.setOnClickListener {
            uploadNote()
        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            ))
        }
        filePickerLauncher.launch(intent)
    }

    private fun uploadNote() {
        val title = titleEditText.text.toString().trim()
        val moduleName = moduleNameEditText.text.toString().trim()
        val moduleCode = moduleCodeEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        if (title.isEmpty()) {
            titleEditText.error = "Title is required"
            return
        }

        if (moduleName.isEmpty()) {
            moduleNameEditText.error = "Module name is required"
            return
        }

        if (moduleCode.isEmpty()) {
            moduleCodeEditText.error = "Module code is required"
            return
        }

        if (description.isEmpty()) {
            descriptionEditText.error = "Description is required"
            return
        }

        // In a real app, you would save the note to database/API here
        val note = Note(
            id = System.currentTimeMillis().toString(),
            title = title,
            moduleCode = moduleCode,
            moduleName = moduleName,
            description = description,
            createdDate = "Today",
            filePath = selectedFileUri?.toString()
        )

        Toast.makeText(this, "Note uploaded successfully!", Toast.LENGTH_SHORT).show()
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
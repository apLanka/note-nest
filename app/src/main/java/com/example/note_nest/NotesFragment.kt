package com.example.note_nest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var searchView: SearchView
    private lateinit var allSpinner: Spinner
    private lateinit var moduleSpinner: Spinner
    private lateinit var dateSpinner: Spinner
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var profileImage: ImageView

    private var notesList = mutableListOf<Note>()
    private var filteredNotesList = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        setupSpinners()
        loadSampleData()
        setupListeners()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewNotes)
        searchView = view.findViewById(R.id.searchView)
        allSpinner = view.findViewById(R.id.spinnerAll)
        moduleSpinner = view.findViewById(R.id.spinnerModule)
        dateSpinner = view.findViewById(R.id.spinnerDate)
        fabAdd = view.findViewById(R.id.fabAdd)
        profileImage = view.findViewById(R.id.profileImage)
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(filteredNotesList) { note ->
            // Navigate to note details
            val intent = Intent(requireContext(), NoteViewActivity::class.java)
            intent.putExtra("note_id", note.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = notesAdapter
    }

    private fun setupSpinners() {
        // Setup All filter spinner
        val allOptions = listOf(
            getString(R.string.filter_all_option_all),
            getString(R.string.filter_all_option_notes),
            getString(R.string.filter_all_option_assignments),
            getString(R.string.filter_all_option_lectures)
        )
        val allAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, allOptions)
        allAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        allSpinner.adapter = allAdapter

        // Setup Module filter spinner
        val moduleOptions = listOf(
            getString(R.string.filter_module_option_module),
            getString(R.string.filter_module_option_dsa),
            getString(R.string.filter_module_option_mad),
            getString(R.string.filter_module_option_se),
            getString(R.string.filter_module_option_esd)
        )
        val moduleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, moduleOptions)
        moduleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        moduleSpinner.adapter = moduleAdapter

        // Setup Date filter spinner
        val dateOptions = listOf(
            getString(R.string.filter_date_option_date),
            getString(R.string.filter_date_option_today),
            getString(R.string.filter_date_option_this_week),
            getString(R.string.filter_date_option_this_month)
        )
        val dateAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dateOptions)
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateAdapter
    }

    private fun loadSampleData() {
        notesList.clear()
        notesList.addAll(
            listOf(
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
                    title = "DSA Lec - 1",
                    moduleCode = "IT2070",
                    moduleName = "Data Structures and Algorithms",
                    description = "Introduction to Data Structures",
                    createdDate = "Today"
                ),
                Note(
                    id = "3",
                    title = "MAD Lec - 1",
                    moduleCode = "IT2070",
                    moduleName = "Mobile Application Development",
                    description = "Introduction to Android Development",
                    createdDate = "Today"
                ),
                Note(
                    id = "4",
                    title = "MAD Lec - 2",
                    moduleCode = "IT2070",
                    moduleName = "Mobile Application Development",
                    description = "Android UI Components",
                    createdDate = "Today"
                )
            )
        )
        filteredNotesList.clear()
        filteredNotesList.addAll(notesList)
        notesAdapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), NoteCreateActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterNotes(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterNotes(newText)
                return true
            }
        })
    }

    private fun filterNotes(query: String?) {
        filteredNotesList.clear()
        if (query.isNullOrBlank()) {
            filteredNotesList.addAll(notesList)
        } else {
            filteredNotesList.addAll(
                notesList.filter { note ->
                    note.title.contains(query, ignoreCase = true) ||
                    note.moduleName.contains(query, ignoreCase = true) ||
                    note.moduleCode.contains(query, ignoreCase = true)
                }
            )
        }
        notesAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): NotesFragment {
            return NotesFragment()
        }
    }
}
package com.example.note_nest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GroupsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var searchView: SearchView
    private lateinit var moduleSpinner: Spinner
    private lateinit var membersSpinner: Spinner
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var profileImage: ImageView

    private var groupsList = mutableListOf<Group>()
    private var filteredGroupsList = mutableListOf<Group>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        loadSampleData()
        setupListeners()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewGroups)
        searchView = view.findViewById(R.id.searchView)
        moduleSpinner = view.findViewById(R.id.spinnerModule)
        membersSpinner = view.findViewById(R.id.spinnerMembers)
        fabAdd = view.findViewById(R.id.fabAdd)
        profileImage = view.findViewById(R.id.profileImage)
    }

    private fun setupRecyclerView() {
        groupsAdapter = GroupsAdapter(filteredGroupsList) { group ->
            // Navigate to group details
            val intent = Intent(requireContext(), GroupViewActivity::class.java)
            intent.putExtra("group_id", group.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = groupsAdapter
    }

    private fun loadSampleData() {
        groupsList.clear()
        groupsList.addAll(
            listOf(
                Group(
                    id = "1",
                    name = "Study Buddies",
                    description = "A group for collaborative studying and sharing notes",
                    memberCount = 12,
                    members = listOf(
                        Member("1", "John Doe"),
                        Member("2", "Jane Smith"),
                        Member("3", "Mike Johnson")
                    ),
                    createdDate = "2024-01-15"
                ),
                Group(
                    id = "2",
                    name = "Study Buddies",
                    description = "DSA focused study group",
                    memberCount = 12,
                    createdDate = "2024-01-10"
                ),
                Group(
                    id = "3",
                    name = "Study Buddies",
                    description = "Mobile app development group",
                    memberCount = 12,
                    createdDate = "2024-01-05"
                ),
                Group(
                    id = "4",
                    name = "Study Buddies",
                    description = "Software engineering study group",
                    memberCount = 12,
                    createdDate = "2024-01-01"
                ),
                Group(
                    id = "5",
                    name = "Study Buddies",
                    description = "General CS study group",
                    memberCount = 12,
                    createdDate = "2023-12-20"
                )
            )
        )
        filteredGroupsList.clear()
        filteredGroupsList.addAll(groupsList)
        groupsAdapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), GroupCreateActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterGroups(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterGroups(newText)
                return true
            }
        })
    }

    private fun filterGroups(query: String?) {
        filteredGroupsList.clear()
        if (query.isNullOrBlank()) {
            filteredGroupsList.addAll(groupsList)
        } else {
            filteredGroupsList.addAll(
                groupsList.filter { group ->
                    group.name.contains(query, ignoreCase = true) ||
                    group.description.contains(query, ignoreCase = true)
                }
            )
        }
        groupsAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): GroupsFragment {
            return GroupsFragment()
        }
    }
}
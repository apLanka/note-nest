package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupsAdapter(
    private val groups: List<Group>,
    private val onGroupClick: (Group) -> Unit
) : RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>() {

    inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val groupImage: ImageView = itemView.findViewById(R.id.groupImage)
        private val groupName: TextView = itemView.findViewById(R.id.textGroupName)
        private val memberCount: TextView = itemView.findViewById(R.id.textMemberCount)

        fun bind(group: Group) {
            groupName.text = group.name
            memberCount.text = "${group.memberCount} members"
            
            // Set the group image
            groupImage.setImageResource(R.drawable.group_img)
            
            itemView.setOnClickListener {
                onGroupClick(group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(groups[position])
    }

    override fun getItemCount(): Int = groups.size
}
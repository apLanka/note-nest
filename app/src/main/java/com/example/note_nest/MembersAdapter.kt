package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MembersAdapter(
    private val members: List<Member>
) : RecyclerView.Adapter<MembersAdapter.MemberViewHolder>() {

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val memberAvatar: ImageView = itemView.findViewById(R.id.memberAvatar)

        fun bind(member: Member) {
            // Set member avatar - using logo as placeholder
            memberAvatar.setImageResource(R.drawable.logo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position])
    }

    override fun getItemCount(): Int = members.size
}
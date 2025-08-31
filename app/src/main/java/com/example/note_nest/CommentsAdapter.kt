package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter(
    private val comments: List<Comment>
) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatar: ImageView = itemView.findViewById(R.id.userAvatar)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val commentMessage: TextView = itemView.findViewById(R.id.commentMessage)
        private val timestamp: TextView = itemView.findViewById(R.id.timestamp)

        fun bind(comment: Comment) {
            userName.text = comment.userName
            commentMessage.text = comment.message
            timestamp.text = comment.timestamp
            // In real app, load avatar image here
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size
}
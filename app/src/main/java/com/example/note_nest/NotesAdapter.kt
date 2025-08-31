package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val notes: List<Note>,
    private val onNoteClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.textNoteTitle)
        private val dateText: TextView = itemView.findViewById(R.id.textNoteDate)
        private val moduleCodeText: TextView = itemView.findViewById(R.id.textModuleCode)

        fun bind(note: Note) {
            titleText.text = note.title
            dateText.text = note.createdDate
            moduleCodeText.text = note.moduleCode
            
            itemView.setOnClickListener {
                onNoteClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}
package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SettingsAdapter(
    private val settings: List<SettingsItem>,
    private val onSettingsClick: (SettingsItem) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    inner class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val settingsIcon: ImageView = itemView.findViewById(R.id.settingsIcon)
        private val settingsTitle: TextView = itemView.findViewById(R.id.settingsTitle)
        private val arrowIcon: ImageView = itemView.findViewById(R.id.arrowIcon)

        fun bind(settingsItem: SettingsItem) {
            settingsIcon.setImageResource(settingsItem.iconResource)
            settingsTitle.text = settingsItem.title
            
            if (settingsItem.hasArrow) {
                arrowIcon.visibility = View.VISIBLE
            } else {
                arrowIcon.visibility = View.GONE
            }
            
            itemView.setOnClickListener {
                onSettingsClick(settingsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(settings[position])
    }

    override fun getItemCount(): Int = settings.size
}
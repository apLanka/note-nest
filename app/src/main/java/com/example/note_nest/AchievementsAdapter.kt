package com.example.note_nest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AchievementsAdapter(
    private val achievements: List<Achievement>
) : RecyclerView.Adapter<AchievementsAdapter.AchievementViewHolder>() {

    inner class AchievementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val achievementIcon: ImageView = itemView.findViewById(R.id.achievementIcon)
        private val achievementTitle: TextView = itemView.findViewById(R.id.achievementTitle)
        private val achievementDescription: TextView = itemView.findViewById(R.id.achievementDescription)
        private val completionCheckmark: ImageView = itemView.findViewById(R.id.completionCheckmark)

        fun bind(achievement: Achievement) {
            achievementIcon.setImageResource(achievement.iconResource)
            achievementTitle.text = achievement.title
            achievementDescription.text = achievement.description
            
            // Show checkmark if completed
            if (achievement.isCompleted) {
                completionCheckmark.visibility = View.VISIBLE
                achievementTitle.alpha = 1.0f
                achievementDescription.alpha = 1.0f
            } else {
                completionCheckmark.visibility = View.GONE
                achievementTitle.alpha = 0.6f
                achievementDescription.alpha = 0.6f
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_achievement, parent, false)
        return AchievementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(achievements[position])
    }

    override fun getItemCount(): Int = achievements.size
}
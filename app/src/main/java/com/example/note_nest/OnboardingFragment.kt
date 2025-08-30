package com.example.note_nest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {
    
    companion object {
        private const val ARG_POSITION = "position"
        
        fun newInstance(position: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val position = arguments?.getInt(ARG_POSITION) ?: 0
        setupContent(view, position)
    }
    
    private fun setupContent(view: View, position: Int) {
        val imageView = view.findViewById<ImageView>(R.id.iv_onboarding)
        val titleTextView = view.findViewById<TextView>(R.id.tv_title)
        val descriptionTextView = view.findViewById<TextView>(R.id.tv_description)
        val actionButton = view.findViewById<Button>(R.id.btn_action)
        
        when (position) {
            0 -> {
                imageView.setImageResource(R.drawable.organize_your_notes_screen_img)
                titleTextView.text = "Organize Your Notes"
                descriptionTextView.text = "Keep all your study materials in one place with our intuitive organization system."
                actionButton.text = "Next"
            }
            1 -> {
                imageView.setImageResource(R.drawable.collaborate_with_peers_screen_img)
                titleTextView.text = "Collaborate with Peers"
                descriptionTextView.text = "Share notes, collaborate in real-time, and create study groups with your classmates."
                actionButton.text = "Next"
            }
            2 -> {
                imageView.setImageResource(R.drawable.ai_powered_screen_img)
                titleTextView.text = "AI-Powered Summaries"
                descriptionTextView.text = "Get instant summaries and key points from your notes with our AI assistant."
                actionButton.text = "Get Started"
            }
        }
        
        actionButton.setOnClickListener {
            (activity as? OnboardingActivity)?.nextPage()
        }
    }
}
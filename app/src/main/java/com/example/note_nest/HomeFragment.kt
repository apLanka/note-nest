package com.example.note_nest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        // Setup click listeners for quick access cards and other interactive elements
        view?.findViewById<View>(R.id.card_mad)?.setOnClickListener {
            // Navigate to MAD notes
        }
        
        view?.findViewById<View>(R.id.card_dsa)?.setOnClickListener {
            // Navigate to DSA notes
        }
        
        view?.findViewById<View>(R.id.card_ps)?.setOnClickListener {
            // Navigate to PS notes
        }
        
        view?.findViewById<View>(R.id.card_esd)?.setOnClickListener {
            // Navigate to ESD notes
        }
        
        view?.findViewById<View>(R.id.tv_see_all_upcoming)?.setOnClickListener {
            // Navigate to upcoming tasks
        }
        
        view?.findViewById<View>(R.id.tv_see_all_notes)?.setOnClickListener {
            // Navigate to all notes
        }
    }
    
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
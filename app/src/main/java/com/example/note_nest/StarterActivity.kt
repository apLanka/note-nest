package com.example.note_nest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StarterActivity : AppCompatActivity() {
    
    private val splashTimeOut: Long = 3000
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val getStartedButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_get_started)
        getStartedButton.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
        
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}
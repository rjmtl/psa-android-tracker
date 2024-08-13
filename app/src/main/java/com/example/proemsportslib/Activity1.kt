package com.example.proemsportslib

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.psa_trakcer.psa.event.ScreenView

class Activity1 : AppCompatActivity() {


    lateinit var nextBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        // Track screen view event
        TrackerManager.getTracker()?.track(ScreenView("Screen One"))
        nextBtn = findViewById(R.id.nextBtn)
        nextBtn.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

    }

    }

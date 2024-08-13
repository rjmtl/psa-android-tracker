package com.example.proemsportslib

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.psa_trakcer.psa.event.ScreenView

class Activity2 : AppCompatActivity() {
    lateinit var next2: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        next2 = findViewById(R.id.next2Btn)


        // Track screen view event
       TrackerManager.getTracker()?.track(ScreenView("Screen Two"))




        next2.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)

        }
    }
}
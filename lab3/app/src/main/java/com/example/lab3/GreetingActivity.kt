package com.example.lab3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        val name = intent.getStringExtra("USER_NAME")

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        greetingTextView.text = "Привіт, $name!"
    }
}
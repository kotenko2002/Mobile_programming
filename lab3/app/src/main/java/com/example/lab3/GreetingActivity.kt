package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        val name = intent.getStringExtra("USER_NAME")
        val surname = intent.getStringExtra("USER_SURNAME")
        val email = intent.getStringExtra("USER_EMAIL")

        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        val detailsButton = findViewById<Button>(R.id.detailsButton)

        greetingTextView.text = "Привіт, $name!"

        detailsButton.setOnClickListener {
            val intent = Intent(this, DetailedInfoActivity::class.java).apply {
                putExtra("USER_NAME", name)
                putExtra("USER_SURNAME", surname)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
        }
    }
}
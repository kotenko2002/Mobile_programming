package com.example.lab3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_info)

        val name = intent.getStringExtra("USER_NAME")
        val surname = intent.getStringExtra("USER_SURNAME")
        val email = intent.getStringExtra("USER_EMAIL")

        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Ім'я: $name\nПрізвище: $surname\nЕлектронна пошта: $email"
    }
}

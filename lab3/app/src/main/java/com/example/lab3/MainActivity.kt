package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogAndToast("onCreate() called")

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val surnameInput = findViewById<EditText>(R.id.surnameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val surname = surnameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Будь ласка, заповніть усі поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Невірний формат електронної пошти", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, GreetingActivity::class.java).apply {
                putExtra("USER_NAME", name)
                putExtra("USER_SURNAME", surname)
                putExtra("USER_EMAIL", email)
            }

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_help -> {
                val intent = Intent(this, HelpActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        LogAndToast("onStart() called")
    }

    override fun onResume() {
        super.onResume()
        LogAndToast("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        LogAndToast("onPause() called")
    }

    override fun onStop() {
        super.onStop()
        LogAndToast("onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogAndToast("onDestroy() called")
    }

    private fun LogAndToast(message: String) {
        //Log.d("MainActivity", message)
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        return email.matches(emailRegex)
    }
}
package com.example.lab4

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DishDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        // Додає кнопку "Назад" в ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("DISH_NAME")
        val description = intent.getStringExtra("DISH_DESCRIPTION")
        val price = intent.getDoubleExtra("DISH_PRICE", 0.0)
        val ingredients = intent.getStringArrayExtra("DISH_INGREDIENTS")?.joinToString(", ")

        findViewById<TextView>(R.id.dish_name).text = name
        findViewById<TextView>(R.id.dish_description).text = description
        findViewById<TextView>(R.id.dish_price).text = price.toString()
        findViewById<TextView>(R.id.dish_ingredients).text = ingredients
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

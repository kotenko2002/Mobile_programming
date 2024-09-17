package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FormFragment.OnDataPassListener {

    private val _recipes = arrayListOf(
        Recipe("1X1", "1X2", "1X3"),
        Recipe("2W1", "2W2", "2W3"),
        Recipe("3G1", "3G2", "3G3"),
        Recipe("4T1", "4T2", "4T3"),
        Recipe("5B1", "5B2", "5B3"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formFragment = FormFragment.newInstance(_recipes)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .commit()
    }

    override fun onDataPass(newValue: Recipe) {
        _recipes.add(newValue)

        val formFragment = FormFragment.newInstance(_recipes)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .commit()
    }
}

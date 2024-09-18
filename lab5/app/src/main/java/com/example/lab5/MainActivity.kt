package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.fragments.FormFragment
import com.example.lab5.fragments.ListFragment

class MainActivity : AppCompatActivity(), FormFragment.OnDataPassListener {
    private val _recipes = RecipeData.recipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formFragment = FormFragment.newInstance(_recipes)
        val listFragment = ListFragment.newInstance(_recipes)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .replace(R.id.bottomFragment, listFragment)
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

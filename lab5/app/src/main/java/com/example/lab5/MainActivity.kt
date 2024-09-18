package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    FormFragment.OnDataPassListener,
    ListFragment.OnDataPassListener,
    DetailFragment.OnDataPassListener
{
    private val _recipes = RecipeData.recipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderFormAndList()
    }

    override fun add(newValue: Recipe) {
        _recipes.add(newValue)
        renderFormAndList()
    }

    override fun openDetailFragment(recipe: Recipe) {
        val formFragment = DetailFragment.newInstance(recipe)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .remove(supportFragmentManager.findFragmentById(R.id.bottomFragment) ?: return)
            .commit()
    }

    override fun delete(recipeId: String) {
        TODO("Not yet implemented")
    }

    private fun renderFormAndList() {
        val formFragment = FormFragment.newInstance(_recipes)
        val listFragment = ListFragment.newInstance(_recipes)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .replace(R.id.bottomFragment, listFragment)
            .commit()
    }
}

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
            .replace(R.id.firstFragment, formFragment)
            .remove(supportFragmentManager.findFragmentById(R.id.secondFragment) ?: return)
            .addToBackStack(null)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun saveChanges(recipe: Recipe) {
        val index = _recipes.indexOfFirst { it.id == recipe.id }
        if (index != -1) {
            _recipes[index] = recipe
            renderFormAndList()
        }
    }

    override fun delete(recipeId: String) {
        val recipeToRemove = _recipes.find { it.id == recipeId }
        if (recipeToRemove != null) {
            _recipes.remove(recipeToRemove)
            renderFormAndList()
        }
    }

    private fun renderFormAndList() {
        val formFragment = FormFragment.newInstance()
        val listFragment = ListFragment.newInstance(_recipes)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .replace(R.id.secondFragment, listFragment)
            .addToBackStack(null)
            .commit()
    }
}

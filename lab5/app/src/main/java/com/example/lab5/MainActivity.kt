package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FormFragment.OnDataPassListener {

    private val stringList = arrayListOf("Apple", "Banana", "Cherry")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formFragment = FormFragment.newInstance(stringList)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .commit()
    }

    override fun onDataPass(newValue: String) {
        stringList.add(newValue)

        val joinedString = stringList.joinToString(",")
        Log.d("MainActivity", "Updated list: $joinedString")

        val formFragment = FormFragment.newInstance(stringList)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFragment, formFragment)
            .commit()
    }
}

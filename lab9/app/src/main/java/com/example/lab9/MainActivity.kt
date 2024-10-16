package com.example.lab9

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.models.PostViewModel
import com.example.lab9.models.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Виклик запиту у корутині
        lifecycleScope.launch {
            try {
                val posts = RetrofitClient.apiService.getPosts(page = 1, limit = 10)
                // Виведення результату в консоль
                posts.forEach { post ->
                    println("Post ID: ${post.id}, Title: ${post.title}")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}
package com.example.lab9.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class PostViewModel : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        try {
            val posts = RetrofitClient.apiService.getPosts(page = 1, limit = 10)
            emit(posts)
        } catch (e: Exception) {
            emit(emptyList<ApiPost>())
        }
    }
}

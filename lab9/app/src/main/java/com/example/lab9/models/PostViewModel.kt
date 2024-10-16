package com.example.lab9.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<ApiPost>>()
    val posts: LiveData<List<ApiPost>> get() = _posts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postApiService: ApiService = retrofit.create(ApiService::class.java)

    fun getPosts() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = postApiService.getPosts()
                _posts.value = response;

                Thread.sleep(2000)
                println("Got " + _posts.value?.size + "posts")
            } catch (e: Exception) {
                println("error")
            } finally {
                _loading.value = false
            }
        }
    }
}

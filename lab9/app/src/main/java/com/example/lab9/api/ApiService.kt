package com.example.lab9.api

import com.example.lab9.models.ApiPost
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int = 10
    ): List<ApiPost>
}

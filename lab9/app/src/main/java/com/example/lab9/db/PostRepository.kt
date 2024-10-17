package com.example.lab9.db

import android.content.ContentValues
import com.example.lab9.models.DbPost

class PostRepository(private val dbHelper: PostDatabaseHelper) {

    fun addPost(post: DbPost) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", post.id)
            put("title", post.title)
            put("body", post.body)
        }
        db.insert("posts", null, values)
    }

    fun getAllPosts(): List<DbPost> {
        val posts = mutableListOf<DbPost>()
        val db = dbHelper.readableDatabase
        val cursor = db.query("posts", null, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val post = DbPost(
                    id = getString(getColumnIndexOrThrow("id")),
                    title = getString(getColumnIndexOrThrow("title")),
                    body = getString(getColumnIndexOrThrow("body"))
                )
                posts.add(post)
            }
        }
        cursor.close()
        return posts
    }

    fun updatePost(post: DbPost) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("title", post.title)
            put("body", post.body)
        }
        db.update("posts", values, "id = ?", arrayOf(post.id))
    }

    fun deletePost(postId: String) {
        val db = dbHelper.writableDatabase
        db.delete("posts", "id = ?", arrayOf(postId))
    }
}
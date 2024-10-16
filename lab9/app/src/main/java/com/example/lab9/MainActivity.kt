package com.example.lab9

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab9.fragments.TestFragment
import com.example.lab9.models.PostViewModel

class MainActivity : AppCompatActivity() {
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        val fetchDataButton = findViewById<Button>(R.id.fetchDataButton)

        postViewModel.posts.observe(this) { posts ->
            //recyclerView.adapter = PostAdapter(posts)
        }

        postViewModel.loading.observe(this) { isLoading ->
            findViewById<View>(R.id.dimView).visibility = if (isLoading) View.VISIBLE else View.GONE
            findViewById<ProgressBar>(R.id.progressBar).visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        fetchDataButton.setOnClickListener {
            postViewModel.getPosts()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TestFragment())
                .commit()
        }

        postViewModel.getPosts()
    }
}

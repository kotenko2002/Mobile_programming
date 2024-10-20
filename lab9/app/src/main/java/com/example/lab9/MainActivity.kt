package com.example.lab9

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab9.databinding.ActivityMainBinding
import com.example.lab9.api.PostViewModel
import com.example.lab9.fragments.db_posts.PostListFragment
import com.example.lab9.fragments.PostsFragment
import com.example.lab9.models.DbPost

class MainActivity : AppCompatActivity(), PostListFragment.OnDataPassListener {
    private lateinit var _binding: ActivityMainBinding
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initGlobalLoader();

        //val fetchDataButton = findViewById<Button>(R.id.fetchDataButton)

        //postViewModel.posts.observe(this) { posts ->
            //recyclerView.adapter = PostAdapter(posts)
        //}

        //fetchDataButton.setOnClickListener {
            //postViewModel.getPosts(1)
        //}

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PostsFragment())
                .commit()
        }

        //postViewModel.getPosts(1)
    }

    private fun initGlobalLoader() {
        postViewModel.loading.observe(this) { isLoading ->
            _binding.dimView.visibility = if (isLoading) View.VISIBLE else View.GONE
            _binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun openDetailPost(post: DbPost) {
        Log.d("PostAdapter", "ID: ${post.id}")
    }
}

package com.example.lab9

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab9.databinding.ActivityMainBinding
import com.example.lab9.api.PostViewModel
import com.example.lab9.fragments.db_posts.PostListFragment
import com.example.lab9.fragments.PostsFragment
import com.example.lab9.fragments.db_posts.EditPostFragment
import com.example.lab9.models.DbPost

class MainActivity : AppCompatActivity(),
    PostListFragment.OnDataPassListener,
    EditPostFragment.OnDataPassListener
{
    private lateinit var _binding: ActivityMainBinding
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initGlobalLoader();

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, PostsFragment())
                .commit()
        }
    }

    private fun initGlobalLoader() {
        postViewModel.loading.observe(this) { isLoading ->
            _binding.dimView.visibility = if (isLoading) View.VISIBLE else View.GONE
            _binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun openDetailPost(post: DbPost) {
        val fragment = EditPostFragment().apply {
            arguments = Bundle().apply {
                putParcelable("post", post)
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}

package com.example.lab9.fragments

import SharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R
import com.example.lab9.adapters.PostAdapter
import com.example.lab9.db.PostDatabaseHelper
import com.example.lab9.db.PostRepository

class PostListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        recyclerView = view.findViewById(R.id.postRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dbHelper = PostDatabaseHelper(requireContext())
        val postRepository = PostRepository(dbHelper)

        // Завантажити початковий список постів
        val posts = postRepository.getAllPosts()
        postAdapter = PostAdapter(posts)
        recyclerView.adapter = postAdapter

        // Слухати зміни в SharedViewModel і оновлювати список постів
        sharedViewModel.newPost.observe(viewLifecycleOwner) { newPost ->
            if (newPost != null) {
                // Оновити список постів з бази даних після додавання нового поста
                val updatedPosts = postRepository.getAllPosts()
                postAdapter.updatePosts(updatedPosts)
            }
        }

        return view
    }
}
package com.example.lab9.fragments.api_posts

import DbPostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab9.R
import com.example.lab9.adapters.ApiPostAdapter
import com.example.lab9.api.PostViewModel
import com.example.lab9.databinding.FragmentAllPostsBinding
import com.example.lab9.db.PostDatabaseHelper
import com.example.lab9.db.PostRepository

class ApiPostsFragment : Fragment() {
    private lateinit var _binding: FragmentAllPostsBinding
    private val postViewModel: PostViewModel by activityViewModels()
    private val dbPostViewModel: DbPostViewModel by activityViewModels()

    private lateinit var postRepository: PostRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllPostsBinding.inflate(inflater)

        val dbHelper = PostDatabaseHelper(requireContext())
        postRepository = PostRepository(dbHelper)

        val recyclerView = _binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val initialAdapter = ApiPostAdapter(emptyList(), dbPostViewModel, postRepository)
        recyclerView.adapter = initialAdapter

        addPaginationButtons()

        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            recyclerView.adapter = ApiPostAdapter(posts, dbPostViewModel, postRepository)
        }

        postViewModel.getPosts(0)

        return _binding.root
    }


    private fun addPaginationButtons() {
        val paginationLayout: LinearLayout = _binding.root.findViewById(R.id.pagination_buttons)

        for (i in 0..9) {
            val fetchDataButton = Button(requireContext()).apply {
                text = (i + 1).toString()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(4, 0, 4, 0)
                }
                minWidth = 0
                minimumWidth = 0
                val paddingValue = if (text.length == 1) 38 else 32
                setPadding(paddingValue, 8, paddingValue, 8)
            }

            fetchDataButton.setOnClickListener {
                postViewModel.getPosts(i)
            }

            paginationLayout.addView(fetchDataButton)
        }
    }
}

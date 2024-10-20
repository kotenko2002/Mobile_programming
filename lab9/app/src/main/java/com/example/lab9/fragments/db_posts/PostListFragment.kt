package com.example.lab9.fragments.db_posts

import DbPostViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R
import com.example.lab9.adapters.DbPostAdapter
import com.example.lab9.db.PostDatabaseHelper
import com.example.lab9.db.PostRepository
import com.example.lab9.models.DbPost

class PostListFragment : Fragment() {
    private var _listener: OnDataPassListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: DbPostAdapter
    private val dbPostViewModel: DbPostViewModel by activityViewModels()

    interface OnDataPassListener {
        fun openDetailPost(post: DbPost)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPassListener) {
            _listener = context
        } else {
            throw RuntimeException("$context must implement OnDataPassListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        recyclerView = view.findViewById(R.id.postRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dbHelper = PostDatabaseHelper(requireContext())
        val postRepository = PostRepository(dbHelper)

        val posts = postRepository.getAllPosts()
        postAdapter = DbPostAdapter(posts) { post ->
            _listener?.openDetailPost(post)
        }

        recyclerView.adapter = postAdapter

        dbPostViewModel.postsChanged.observe(viewLifecycleOwner) {
            val updatedPosts = postRepository.getAllPosts()
            postAdapter.updatePosts(updatedPosts)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

}
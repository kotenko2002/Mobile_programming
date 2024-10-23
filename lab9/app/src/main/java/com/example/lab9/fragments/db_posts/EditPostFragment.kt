package com.example.lab9.fragments.db_posts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.lab9.databinding.FragmentEditPostBinding
import com.example.lab9.db.PostRepository
import com.example.lab9.db.PostDatabaseHelper
import com.example.lab9.models.DbPost

class EditPostFragment : Fragment() {
    private var _binding: FragmentEditPostBinding? = null
    private var _listener: OnDataPassListener? = null
    private val binding get() = _binding!!

    private var post: DbPost? = null
    private lateinit var postRepository: PostRepository

    interface OnDataPassListener {
        fun back()
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
        _binding = FragmentEditPostBinding.inflate(inflater, container, false)

        val dbHelper = PostDatabaseHelper(requireContext())
        postRepository = PostRepository(dbHelper)

        post = arguments?.getParcelable("post")

        binding.inputTitle.setText(post?.title)
        binding.inputBody.setText(post?.body)

        binding.backButton.setOnClickListener {
            _listener?.back()
        }

        binding.btnSaveChanges.setOnClickListener {
            val updatedTitle = binding.inputTitle.text.toString()
            val updatedBody = binding.inputBody.text.toString()

            if (updatedTitle.isNotEmpty() && updatedBody.isNotEmpty()) {
                post?.let {
                    it.title = updatedTitle
                    it.body = updatedBody
                    postRepository.updatePost(it)
                    Toast.makeText(requireContext(), "Зміни збережено", Toast.LENGTH_SHORT).show()
                    Log.d("EditPostFragment", "Post updated: $updatedTitle, $updatedBody")
                    _listener?.back()
                }
            } else {
                Toast.makeText(requireContext(), "Заповніть всі поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDelete.setOnClickListener {
            post?.let {
                postRepository.deletePost(it.id)
                Toast.makeText(requireContext(), "Пост видалено", Toast.LENGTH_SHORT).show()
                Log.d("EditPostFragment", "Post deleted: ${it.id}")
                _listener?.back()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }
}

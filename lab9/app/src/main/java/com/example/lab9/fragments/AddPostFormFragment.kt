package com.example.lab9.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab9.databinding.FragmentAddPostFormBinding
import com.example.lab9.db.PostRepository
import com.example.lab9.db.PostDatabaseHelper
import com.example.lab9.models.DbPost
import java.util.*

class AddPostFormFragment : Fragment() {

    private var _binding: FragmentAddPostFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPostFormBinding.inflate(inflater, container, false)

        val dbHelper = PostDatabaseHelper(requireContext())
        val postRepository = PostRepository(dbHelper)

        binding.btnAddPost.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val body = binding.inputBody.text.toString()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                val newPost = DbPost(id = UUID.randomUUID().toString(), title = title, body = body)
                postRepository.addPost(newPost)
                Toast.makeText(requireContext(), "Пост додано!", Toast.LENGTH_SHORT).show()

                binding.inputTitle.text.clear()
                binding.inputBody.text.clear()
            } else {
                Toast.makeText(requireContext(), "Заповніть всі поля", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
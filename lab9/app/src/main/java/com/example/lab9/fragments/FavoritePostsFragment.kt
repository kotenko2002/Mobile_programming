package com.example.lab9.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab9.databinding.FragmentFavoritePostsBinding

class FavoritePostsFragment : Fragment() {
    private lateinit var _binding: FragmentFavoritePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritePostsBinding.inflate(inflater)
        return _binding.root
    }
}
package com.example.lab9.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab9.databinding.FragmentAllPostsBinding

class AllPostsFragment : Fragment() {
    private lateinit var _binding: FragmentAllPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllPostsBinding.inflate(inflater)
        return _binding.root
    }
}

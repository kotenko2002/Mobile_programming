package com.example.controlwork.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.controlwork.databinding.FragmentFollowedLocationsBinding
import com.example.controlwork.modelViews.main.FollowedLocationsViewModel

class FollowedLocationsFragment : Fragment() {
    private lateinit var _binding: FragmentFollowedLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val followedLocationsViewModel =
            ViewModelProvider(this).get(FollowedLocationsViewModel::class.java)

        _binding = FragmentFollowedLocationsBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textFollowedLocations
        followedLocationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}
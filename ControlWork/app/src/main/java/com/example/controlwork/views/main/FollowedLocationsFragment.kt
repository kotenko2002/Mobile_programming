package com.example.controlwork.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlwork.databinding.FragmentFollowedLocationsBinding
import com.example.controlwork.modelViews.main.FollowedLocationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowedLocationsFragment : Fragment() {
    private lateinit var _binding: FragmentFollowedLocationsBinding
    private val _followedLocationsViewModel: FollowedLocationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowedLocationsBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val recyclerView = _binding.recyclerViewFollowedLocations
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        _followedLocationsViewModel.getFollowedLocations().observe(viewLifecycleOwner, Observer { locations ->
            recyclerView.adapter = FollowedLocationsListAdapter(locations)
        })

        return root
    }
}
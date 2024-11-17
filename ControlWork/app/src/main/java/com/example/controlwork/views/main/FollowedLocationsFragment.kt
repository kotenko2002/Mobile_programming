package com.example.controlwork.views.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlwork.databinding.FragmentFollowedLocationsBinding
import com.example.controlwork.modelViews.main.FollowedLocationsViewModel
import com.example.controlwork.models.location.Location
import com.example.controlwork.views.locationWeather.LocationWeatherActivity
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
            recyclerView.adapter = FollowedLocationsListAdapter(locations, ::onLocationClick)
        })

        return root
    }

    private fun onLocationClick(location: Location) {
        val intent = Intent(activity, LocationWeatherActivity::class.java)
        intent.putExtra("LOCATION_ID", location.id)
        startActivity(intent)
    }
}
package com.example.controlwork.views.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlwork.databinding.FragmentSearchBinding
import com.example.controlwork.modelViews.main.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var _binding: FragmentSearchBinding
    private val _searchViewModel: SearchViewModel by viewModels()
    private lateinit var _adapter: SearchLocationsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        _adapter = SearchLocationsListAdapter { location ->
            _searchViewModel.followOnLocation(location)
            _binding.locationSearchInput.text.clear()
        }

        _binding.locationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        _binding.locationsRecyclerView.adapter = _adapter

        _binding.locationSearchInput.addTextChangedListener { text ->
            performSearch(text.toString())
        }

        return root
    }

    private fun performSearch(query: String) {
        if (query.length >= 2) {
            _searchViewModel.searchLocations(query).observe(viewLifecycleOwner) { locations ->
                Log.d("SearchFragment", "Found locations: $locations")
                _adapter.submitList(locations)
            }
        } else {
            _adapter.submitList(emptyList())
        }
    }
}

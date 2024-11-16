package com.example.controlwork.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.controlwork.databinding.FragmentSearchBinding
import com.example.controlwork.models.city.City
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding
    private val _searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textSearch
        _searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val testCity = City(
            id = 1,
            name = "Kyiv",
            country = "Ukraine"
        )
        _searchViewModel.upsertCity(testCity)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _searchViewModel.getWeatherDataByCityId()
        observeWeatherData()
    }

    private fun observeWeatherData() {
        _searchViewModel.observeWeatherLiveData().observe(viewLifecycleOwner
        ) { value -> Log.d("TEST3", value.toString()) }
    }
}

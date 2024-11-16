package com.example.controlwork.views.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.controlwork.databinding.FragmentSearchBinding
import com.example.controlwork.modelViews.main.SearchViewModel
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

        /*
        val testCity = City(
            id = 3,
            name = "Sumy",
            country = "Ukraine"
        )
        _searchViewModel.upsertCity(testCity)
         */

        _searchViewModel.searchCities("z").observe(viewLifecycleOwner) { cities ->
            Log.d("SearchFragment", "Found cities: $cities")
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val cityId = 613273
        _searchViewModel.getWeatherDataByCityId(cityId)
        observeWeatherData()
         */

        //observeCities()
    }

    private fun observeWeatherData() {
        _searchViewModel.observeWeatherLiveData().observe(viewLifecycleOwner
        ) { value -> Log.d("TEST3", value.toString()) }
    }

    private fun observeCities() {
        _searchViewModel.getAllCities().observe(viewLifecycleOwner) { cities ->
            cities.forEach { city ->
                Log.d("SearchFragment", "Id: ${city.id}, City: ${city.name}, Country: ${city.country}")
            }
        }
    }

}

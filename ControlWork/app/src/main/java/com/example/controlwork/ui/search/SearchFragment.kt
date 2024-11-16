package com.example.controlwork.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.controlwork.databinding.FragmentSearchBinding
import com.example.controlwork.models.city.City
import com.example.controlwork.models.weather.WeatherData
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

        // Виклик методу upsertCity із захардкодженим екземпляром City
        val testCity = City(
            id = 1,
            name = "Kyiv",
            country = "Ukraine"
        )
        _searchViewModel.upsertCity(testCity)

        return root
    }
}

/*
class SearchFragment : Fragment() {
    private lateinit var _binding: FragmentSearchBinding
    private lateinit var _searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _searchViewModel.getWeatherDataByCityId()
        observeWeatherData()
    }

    private fun observeWeatherData() {
        _searchViewModel.observeWeatherLiveData().observe(viewLifecycleOwner, object: Observer<WeatherData>{
            override fun onChanged(value: WeatherData) {
                Log.d("TEST1", value.toString())
            }
        })
    }
}
 */
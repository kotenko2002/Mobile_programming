package com.example.controlwork.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.controlwork.databinding.FragmentSearchBinding
import com.example.controlwork.models.weatherData.WeatherData
import com.example.controlwork.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var _binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        RetrofitInstance.api.getWeatherDataByCityId().enqueue(object: Callback<WeatherData>{
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val data = response.body();

                if (data != null) {
                    Log.d("TEST", data.toString())
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("SearchFragment", t.message.toString())
            }
        })

        val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        val textView: TextView = _binding.textSearch
        searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}
package com.example.controlwork.views.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlwork.R
import com.example.controlwork.models.city.City

class CityAdapter(private val onCityClick: (City) -> Unit) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cities: List<City> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newCities: List<City>) {
        cities = newCities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, onCityClick)
    }

    override fun getItemCount(): Int = cities.size

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)

        @SuppressLint("SetTextI18n")
        fun bind(city: City, onCityClick: (City) -> Unit) {
            cityName.text = "${city.name} ${getCountryFlag(city.country)}"
            itemView.setOnClickListener { onCityClick(city) }
        }

        private fun getCountryFlag(countryCode: String): String {
            val upperCaseCode = countryCode.uppercase()
            if (upperCaseCode.length != 2) {
                throw IllegalArgumentException("Country code must be a two-letter ISO code.")
            }

            if (upperCaseCode == "RU") {
                return "\uD83C\uDFAA"
            }

            return upperCaseCode.map {
                    char -> 0x1F1E6 + (char - 'A')
            }.joinToString("") {
                String(Character.toChars(it))
            }
        }
    }
}

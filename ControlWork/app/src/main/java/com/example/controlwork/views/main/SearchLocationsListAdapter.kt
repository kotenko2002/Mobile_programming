package com.example.controlwork.views.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlwork.R
import com.example.controlwork.infrastructure.utils.TextHelper.Companion.getCountryFlag
import com.example.controlwork.models.location.Location

class SearchLocationsListAdapter(private val onLocationClick: (Location) -> Unit) :
    RecyclerView.Adapter<SearchLocationsListAdapter.LocationViewHolder>() {

    private var locations: List<Location> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newLocation: List<Location>) {
        locations = newLocation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_location_list_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location, onLocationClick)
    }

    override fun getItemCount(): Int = locations.size

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val locationName: TextView = itemView.findViewById(R.id.locationName)

        @SuppressLint("SetTextI18n")
        fun bind(location: Location, onLocationClick: (Location) -> Unit) {
            locationName.text = "${location.name} ${getCountryFlag(location.country)}"
            itemView.setOnClickListener { onLocationClick(location) }
        }
    }
}

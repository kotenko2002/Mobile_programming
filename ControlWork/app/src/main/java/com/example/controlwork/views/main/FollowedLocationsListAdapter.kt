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

class FollowedLocationsListAdapter(
    private val _locations: List<Location>,
    private val onLocationClick: (Location) -> Unit
) : RecyclerView.Adapter<FollowedLocationsListAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.followed_location_list_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(_locations[position], onLocationClick)
    }

    override fun getItemCount(): Int = _locations.size

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val locationName: TextView = itemView.findViewById(R.id.textViewLocationName)

        @SuppressLint("SetTextI18n")
        fun bind(location: Location, onLocationClick: (Location) -> Unit) {
            locationName.text = "${location.name} ${getCountryFlag(location.country)}"
            itemView.setOnClickListener { onLocationClick(location) }
        }
    }
}
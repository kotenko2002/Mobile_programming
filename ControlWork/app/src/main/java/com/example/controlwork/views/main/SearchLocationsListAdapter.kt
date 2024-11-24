package com.example.controlwork.views.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.controlwork.R
import com.example.controlwork.infrastructure.utils.TextHelper.Companion.getCountryFlag
import com.example.controlwork.models.location.Location

class SearchLocationsListAdapter(private val _onLocationClick: (Location) -> Unit) :
    RecyclerView.Adapter<SearchLocationsListAdapter.LocationViewHolder>() {

    private var _locations: List<Location> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newLocation: List<Location>) {
        _locations = newLocation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_location_list_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = _locations[position]
        holder.bind(location, _onLocationClick)
    }

    override fun getItemCount(): Int = _locations.size

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val locationName: TextView = itemView.findViewById(R.id.locationName)

        @SuppressLint("SetTextI18n")
        fun bind(location: Location, onLocationClick: (Location) -> Unit) {
            locationName.text = "${location.name} ${getCountryFlag(location.country)}"
            itemView.setOnClickListener {
                onLocationClick(location)
                Toast.makeText( itemView.context, "${location.name} has been added to the monitored locations list", Toast.LENGTH_LONG ).show()
            }
        }
    }
}

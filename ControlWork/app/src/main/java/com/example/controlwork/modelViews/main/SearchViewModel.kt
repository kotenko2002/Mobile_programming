package com.example.controlwork.modelViews.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlwork.infrastructure.db.FollowedLocationDao
import com.example.controlwork.infrastructure.db.LocationDao
import com.example.controlwork.models.location.FollowedLocation
import com.example.controlwork.models.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val _locationDao: LocationDao,
    private val _followedLocationDao: FollowedLocationDao
) : ViewModel() {

    fun searchLocations(prefix: String): LiveData<List<Location>> {
        return _locationDao.searchLocationsByName(prefix)
    }

    fun followOnLocation(location: Location) {
        val newFollowedLocation = FollowedLocation(locationId = location.id)

        viewModelScope.launch {
            _followedLocationDao.upsertFollowedLocation(newFollowedLocation)
        }
    }
}

/*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlwork.infrastructure.db.CityDao
import com.example.controlwork.models.city.City
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cityDao: CityDao
) : ViewModel() {
    fun upsertCity(city: City) {
        viewModelScope.launch {
            cityDao.upsertCity(city)
        }
    }

    fun getAllCities(): LiveData<List<City>> {
        return cityDao.getAllCities()
    }

    fun searchCities(prefix: String): LiveData<List<City>> {
        return cityDao.searchCitiesByName(prefix)
    }
}
 */
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
import com.example.controlwork.models.weather.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.example.controlwork.infrastructure.db.CityDao
import com.example.controlwork.models.city.City
import com.example.controlwork.infrastructure.retrofit.WeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cityDao: CityDao,
    private val weatherApi: WeatherApi
) : ViewModel() {
    private var _weatherLiveData = MutableLiveData<WeatherData>()

    fun getWeatherDataByCityId(cityId: Int) {
        weatherApi.getWeatherDataByCityId(cityId).enqueue(object: Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val data = response.body();

                if (data != null) {
                    _weatherLiveData.value = data!!;
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("SearchFragment", t.message.toString())
            }
        })
    }

    fun observeWeatherLiveData():LiveData<WeatherData> {
        return _weatherLiveData;
    }

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
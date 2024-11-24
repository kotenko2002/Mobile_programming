package com.example.controlwork.modelViews.locationWeather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlwork.infrastructure.db.FollowedLocationDao
import com.example.controlwork.infrastructure.retrofit.WeatherApi
import com.example.controlwork.models.weather.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class LocationWeatherViewModel @Inject constructor(
    private val _weatherApi: WeatherApi,
    private val _followedLocationDao: FollowedLocationDao
) : ViewModel() {
    private var _weatherLiveData = MutableLiveData<WeatherData>()

    fun getWeatherDataByLocationId(cityId: Int) {
        _weatherApi.getWeatherDataByLocationId(cityId).enqueue(object: Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val data = response.body();

                if (data != null) {
                    _weatherLiveData.value = data!!;
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("LocationWeatherViewModel", t.message.toString())
            }
        })
    }

    fun observeWeatherLiveData():LiveData<WeatherData> {
        return _weatherLiveData;
    }

    fun stopFollowingLocation(locationId: Int) {
        viewModelScope.launch {
            _followedLocationDao.deleteFollowedLocation(locationId)
        }
    }
}

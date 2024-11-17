package com.example.controlwork.modelViews.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.controlwork.infrastructure.db.FollowedLocationDao
import com.example.controlwork.models.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowedLocationsViewModel @Inject constructor(
    private val followedLocationDao: FollowedLocationDao
) : ViewModel() {
    fun getFollowedLocations(): LiveData<List<Location>> {
        return followedLocationDao.getFollowedLocationsWithNames()
    }
}

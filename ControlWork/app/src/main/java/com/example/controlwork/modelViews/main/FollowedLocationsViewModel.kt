package com.example.controlwork.modelViews.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FollowedLocationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is followed locations Fragment"
    }
    val text: LiveData<String> = _text
}
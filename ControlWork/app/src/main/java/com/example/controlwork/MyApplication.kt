package com.example.controlwork

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // Контейнер Hilt ініціалізується тут
}
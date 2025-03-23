package com.geeks.noteapp

import android.app.Application
import com.geeks.noteapp.utils.Preference

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Preference()
        sharedPreferences.unit(this)
    }
}
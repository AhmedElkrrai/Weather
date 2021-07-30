package com.example.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.repository.WeatherRepository

class WeatherViewModelProviderFactory(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}
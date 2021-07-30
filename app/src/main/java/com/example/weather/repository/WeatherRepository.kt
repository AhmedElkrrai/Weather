package com.example.weather.repository

import com.example.weather.api.RetrofitInstance
import com.example.weather.util.Constants.Companion.API_KEY

class WeatherRepository {
    suspend fun getWeatherByLocation(location: String) =
        RetrofitInstance.API_SERVICE.getWeatherByLocation(API_KEY, location, "en")
}
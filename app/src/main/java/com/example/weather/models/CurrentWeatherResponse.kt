package com.example.weather.models

data class CurrentWeatherResponse(
    val current: Current,
    val location: Location,
    val request: Request
)
package com.example.weather.api

import com.example.weather.models.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("current")
    suspend fun getWeatherByLocation(
        @Query("access_key")
        apiKey: String,
        @Query("query")
        location: String,
        @Query("lang")
        languageCode: String
    ): Response<CurrentWeatherResponse>
}
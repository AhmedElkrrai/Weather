package com.example.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.CurrentWeatherResponse
import com.example.weather.repository.WeatherRepository
import com.example.weather.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val weather: MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()

    init {
        getStrikerByName("Cairo")
    }

    fun getStrikerByName(location: String) = viewModelScope.launch {
        val response = repository.getWeatherByLocation(location)
        weather.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<CurrentWeatherResponse>): Resource<CurrentWeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
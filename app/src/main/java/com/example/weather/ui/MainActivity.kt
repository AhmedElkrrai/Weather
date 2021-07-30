package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.models.CurrentWeatherResponse
import com.example.weather.repository.WeatherRepository
import com.example.weather.util.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val footballRepository = WeatherRepository()
        val viewModelProviderFactory = WeatherViewModelProviderFactory(footballRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(WeatherViewModel::class.java)

        viewModel.weather.observe(this, { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let { publishData(it) }
                }
                is Resource.Error -> {
                    response.message?.let { Log.e("sadBugs", "An error occurred: $it") }
                }
            }
        })

        ibSearch.setOnClickListener {
            val query = etSearchBar.editableText.toString()
            if (query.isNotEmpty()) {
                viewModel.getStrikerByName(query)
                etSearchBar.setText("")
            }
        }
    }

    private fun publishData(response: CurrentWeatherResponse) {
        tvLocation.text = response.location.region
        tvWeatherState.text = response.current.weather_descriptions[0]

        val temperature = response.current.temperature
        tvWeatherDegree.text = "$temperature°C"

        val feelsLikeDegree = response.current.feelslike
        tvFeelsLikeDegree.text = "Feels like $feelsLikeDegree°C"

        val windDir = response.current.wind_dir
        val windSpeed = response.current.wind_speed
        tvWind.text = "$windDir, $windSpeed kph"

        val humidity = response.current.humidity
        tvHumidity.text = "$humidity°C"

        val visibility = response.current.visibility
        tvVisibility.text = "$visibility km"
    }
}
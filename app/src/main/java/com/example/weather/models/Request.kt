package com.example.weather.models

data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)
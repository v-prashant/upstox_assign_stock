package com.example.trendingapp.api

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
)
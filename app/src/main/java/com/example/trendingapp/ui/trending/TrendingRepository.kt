package com.example.trendingapp.ui.trending

import com.example.trendingapp.api.APIService
import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: APIService) {
     suspend fun getRepositories() = apiService.getRepositories("stars", "100")
}
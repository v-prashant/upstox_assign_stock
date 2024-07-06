package com.example.trendingapp.ui.ui.holding

import com.example.trendingapp.api.APIService
import javax.inject.Inject

class HoldingRepository @Inject constructor(private val apiService: APIService) {
     suspend fun getHoldings() = apiService.getHoldings()
}
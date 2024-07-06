package com.example.trendingapp.api

import com.example.trendingapp.network.response.GetHoldingsResponse
import retrofit2.Response
import retrofit2.http.GET


interface APIService {
    // https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/
    @GET(".")
    suspend fun getHoldings(): Response<GetHoldingsResponse>
}
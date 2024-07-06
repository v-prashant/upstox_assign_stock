package com.example.trendingapp.network.response

data class GetHoldingsResponse(
    val data: Data? = null
)

data class Data(
    val userHolding: List<UserHoldingData>?
)

data class UserHoldingData(
    val symbol: String?,
    val quantity: Int?,
    val ltp: Double?,
    val avgPrice: Double?,
    val close: Double?
)
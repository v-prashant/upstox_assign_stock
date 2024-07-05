package com.example.trendingapp.api

import com.example.trendingapp.network.response.GetRepositoriesResponse
import com.example.trendingapp.utils.AppConstant.REPOSITORIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    // https://api.github.com/search/repositories?q=stars&per_page=100
    @GET(REPOSITORIES)
    suspend fun getRepositories(@Query("q") q: String, @Query("per_page") perPage: String): Response<GetRepositoriesResponse>
}
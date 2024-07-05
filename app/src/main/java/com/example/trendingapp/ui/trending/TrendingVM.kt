package com.example.trendingapp.ui.trending

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingapp.api.Resource
import com.example.trendingapp.network.response.GetRepositoriesResponse
import com.example.trendingapp.utils.extension_functions.setError
import com.example.trendingapp.utils.extension_functions.setLoading
import com.example.trendingapp.utils.extension_functions.setStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingVM @Inject constructor(val repository: TrendingRepository) :
    ViewModel() {

    val getRepositoriesLiveData = MutableLiveData<Resource<GetRepositoriesResponse>>()
    fun getRepositories() {
        getRepositoriesLiveData.setLoading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRepositories()
                getRepositoriesLiveData.setStatus(response)
            } catch (exception: Exception) {
                val response = GetRepositoriesResponse()
                getRepositoriesLiveData.setError(message = "No Internet Connection")
                Log.d("NETWORK_EXCEPTION", exception.stackTraceToString())
            }
        }
    }

}
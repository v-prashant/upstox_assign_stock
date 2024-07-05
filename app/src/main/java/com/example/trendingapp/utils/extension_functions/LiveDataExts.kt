package com.example.trendingapp.utils.extension_functions

import com.example.trendingapp.api.Status
import androidx.lifecycle.MutableLiveData
import com.example.trendingapp.api.Resource
import retrofit2.Response

fun <T> MutableLiveData<Resource<T>>.setStatus(data: Response<T>) {

    if (data.isSuccessful) {
        postValue(Resource(status = Status.SUCCESS, data= data.body(), message = data.message()))
    } else {
        postValue(Resource(status = Status.FAILURE, data= data.body(), message = data.message()))
    }

}

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(Status.ERROR, message = message))

fun <T> MutableLiveData<Resource<T>>.setLoading(message: String? = null) =
    postValue(Resource(Status.LOADING, message = message))

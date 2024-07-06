package com.example.trendingapp.ui.ui.holding

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingapp.api.Resource
import com.example.trendingapp.network.response.GetHoldingsResponse
import com.example.trendingapp.network.response.UserHoldingData
import com.example.trendingapp.ui.Application
import com.example.trendingapp.utils.SharedPreferenceUtil
import com.example.trendingapp.utils.extension_functions.setError
import com.example.trendingapp.utils.extension_functions.setLoading
import com.example.trendingapp.utils.extension_functions.setStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HoldingViewModel @Inject constructor(val repository: HoldingRepository) : ViewModel() {

    val getRepositoriesLiveData = MutableLiveData<Resource<GetHoldingsResponse>>()
    var dataList: ArrayList<HoldingItems>? = ArrayList()

    fun getHoldings() {
        getRepositoriesLiveData.setLoading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getHoldings()
                getRepositoriesLiveData.setStatus(response)
                SharedPreferenceUtil.setSharedPrefObject(Application.getInstance(), SharedPreferenceUtil.Holding_Data, response.body()?.data?.userHolding)
            } catch (exception: Exception) {
                getRepositoriesLiveData.setError(message = "No Internet Connection")
                Log.d("NETWORK_EXCEPTION", exception.stackTraceToString())
            }
        }
    }

    fun filterData(userHoldingData: List<UserHoldingData>): ArrayList<HoldingItems> {
        val list = ArrayList<HoldingItems>()
        for(item in userHoldingData){
            var pl:Double? = null
            if(item.ltp != null && item.close != null && item.quantity != null)
                pl = (item.ltp - item.close)* item.quantity

            list.add(HoldingItems(item.symbol,
                item.quantity.toString(),
                String.format("%.2f", item.ltp),
                String.format("%.2f", pl),
                item.avgPrice,
                item.close))
        }

        return list
    }

}
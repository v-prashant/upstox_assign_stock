package com.example.trendingapp.ui.ui.holding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trendingapp.api.Status
import com.example.trendingapp.databinding.FragmentHoldingBinding
import com.example.trendingapp.network.response.UserHoldingData
import com.example.trendingapp.ui.Application
import com.example.trendingapp.ui.HoldingBottomSheetFragment
import com.example.trendingapp.ui.StockActivity
import com.example.trendingapp.utils.AppConstant
import com.example.trendingapp.utils.SharedPreferenceUtil
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HoldingFragment : Fragment() {

    @Inject
    lateinit var viewModel: HoldingViewModel

    private val binding: FragmentHoldingBinding by lazy {
        FragmentHoldingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
        getData()
        observeData()
    }

    private fun onClickListeners() {
        binding.bsPl.setOnClickListener {
            val bs = HoldingBottomSheetFragment()
            bs.arguments = processData()
            bs.show(parentFragmentManager, "HoldingBottomSheetFragment")
        }
    }

    private fun processData(): Bundle {
        var currValue = 0.0
        var totalInvestment = 0.0
        var todayPL = 0.0
        if(viewModel.dataList != null){
            for(item in viewModel.dataList!!){
                if(item.ltp != null && item.qty != null)
                    currValue += (item.ltp.toDouble() * item.qty.toDouble())
                if(item.avgPrice != null && item.qty != null)
                    totalInvestment += (item.avgPrice.toDouble() * item.qty.toDouble())
                if(item.close != null && item.ltp != null && item.qty != null)
                    todayPL += (item.close.toDouble() - item.ltp.toDouble()) * item.qty.toDouble()
            }
        }

        val bundle = Bundle()
        bundle.putString(AppConstant.CURR_VALUE, String.format("%.2f", currValue))
        bundle.putString(AppConstant.TOTAL_INVESTMENT, String.format("%.2f", totalInvestment))
        bundle.putString(AppConstant.TODAY_PL, String.format("%.2f", todayPL))
        bundle.putString(AppConstant.TOTAL_PL, String.format("%.2f", (currValue-totalInvestment)))

        return bundle
    }

    private fun getData() {
         viewModel.getHoldings()
    }

    private fun observeData() {
        viewModel.getRepositoriesLiveData.observe(requireActivity()){

            when (it.status) {
                Status.LOADING -> {
                    (requireActivity() as StockActivity).showProgress()
                }
                Status.FAILURE -> {
                    (requireActivity() as StockActivity).showErrorMessage(it.message)
                }
                Status.ERROR -> {
                    // no internet
                    val typeToken = object : TypeToken<ArrayList<UserHoldingData>?>() {}
                    val data = SharedPreferenceUtil.getSharedPrefObject(Application.getInstance(), SharedPreferenceUtil.Holding_Data, typeToken)
                    if(data != null){
                        viewModel.dataList = viewModel.filterData(data)
                        initData()
                    }else{
                        (requireActivity() as StockActivity).showErrorMessage(it.message)
                    }
                }
                Status.SUCCESS -> {
                    it.data?.data?.userHolding.apply {
                        viewModel.dataList = this?.let { it1 -> viewModel.filterData(it1) }

                        if(viewModel.dataList != null)
                            initData()
                        else
                            (requireActivity() as StockActivity).showErrorMessage("Invalid Response")
                    }
                }
            }
            (requireActivity() as StockActivity).hideProgress()

        }
    }

    private fun initData() {
        val adapter = HoldingAdapter(requireContext(), viewModel.dataList!!)
        binding.rvHolding.adapter = adapter
    }

    companion object {
        fun newInstance() = HoldingFragment()
    }

}
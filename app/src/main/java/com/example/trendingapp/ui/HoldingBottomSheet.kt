package com.example.trendingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trendingapp.databinding.BottomsheetInfoBinding
import com.example.trendingapp.utils.AppConstant
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HoldingBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: BottomsheetInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.tvCurrValueValue.text = arguments?.getString(AppConstant.CURR_VALUE)
        binding.tvTotalInvestmentValue.text = arguments?.getString(AppConstant.TOTAL_INVESTMENT)
        binding.tvTodayPlValue.text = arguments?.getString(AppConstant.TODAY_PL)
        binding.tvPlValue.text = arguments?.getString(AppConstant.TOTAL_PL)
    }

}
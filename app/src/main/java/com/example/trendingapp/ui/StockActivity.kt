package com.example.trendingapp.ui

import android.os.Bundle
import com.example.trendingapp.R
import com.example.trendingapp.base.BaseActivity
import com.example.trendingapp.ui.ui.holding.HoldingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HoldingFragment.newInstance())
                .commitNow()
        }
    }
}
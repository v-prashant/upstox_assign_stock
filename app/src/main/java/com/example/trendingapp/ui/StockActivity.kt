package com.example.trendingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trendingapp.R
import com.example.trendingapp.ui.ui.holding.HoldingFragment

class StockActivity : AppCompatActivity() {

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
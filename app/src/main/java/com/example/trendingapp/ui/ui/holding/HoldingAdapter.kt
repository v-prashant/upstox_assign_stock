package com.example.trendingapp.ui.ui.holding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trendingapp.R
import com.example.trendingapp.databinding.ItemHoldingBinding

class HoldingAdapter(var context: Context, val dataList: ArrayList<HoldingItems>) : RecyclerView.Adapter<HoldingAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoldingAdapterViewHolder {
        val binding = DataBindingUtil.inflate<ItemHoldingBinding>(
            LayoutInflater.from(context), R.layout.item_holding,
            parent, false
        )
        return HoldingAdapterViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
         return dataList.size
    }

    override fun onBindViewHolder(holder: HoldingAdapterViewHolder, position: Int) {
         with(holder.binding){
             tvCompany.text = dataList[holder.adapterPosition].company
             tvLtp.text = "LTP:-  ₹ "+dataList[holder.adapterPosition].ltp
             tvNetQty.text = "Net qty: "+dataList[holder.adapterPosition].qty
             tvPl.text = "P&L:-  ₹ "+dataList[holder.adapterPosition].pl
         }
    }

}

class HoldingAdapterViewHolder(var binding: ItemHoldingBinding) :
    RecyclerView.ViewHolder(binding.root)

data class HoldingItems(val company: String?, val qty: String?, val ltp: String?, val pl: String?,
                        val avgPrice: Double?, val close: Double?)




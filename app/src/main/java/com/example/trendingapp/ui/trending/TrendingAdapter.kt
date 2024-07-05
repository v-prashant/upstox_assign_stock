package com.example.trendingapp.ui.trending

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trendingapp.R
import com.example.trendingapp.databinding.ItemTrendingBinding

class TrendingAdapter(var context: Context, val dataList: ArrayList<TrendingItems>) : RecyclerView.Adapter<TrendingAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingAdapterViewHolder {
        val binding = DataBindingUtil.inflate<ItemTrendingBinding>(
            LayoutInflater.from(context), R.layout.item_trending,
            parent, false
        )
        return TrendingAdapterViewHolder(binding)
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

    override fun onBindViewHolder(holder: TrendingAdapterViewHolder, position: Int) {
         with(holder.binding){
             tvName.text = dataList[holder.adapterPosition].name
             tvTitle.text = dataList[holder.adapterPosition].title

             tvDescription.text = dataList[holder.adapterPosition].description
             tvLanguage.text = dataList[holder.adapterPosition].language
             tvStar.text = dataList[holder.adapterPosition].star
             tvFork.text = dataList[holder.adapterPosition].fork

             root.setOnClickListener {
                 notifyItemChanged(-1)
                 if(dataList[holder.adapterPosition].cardStatus == "HIDE"){
                     clInfo.visibility = View.VISIBLE
                     dataList[holder.adapterPosition].cardStatus = "VISIBLE"
                 }else{
                     clInfo.visibility = View.GONE
                     dataList[holder.adapterPosition].cardStatus = "HIDE"
                 }
             }

         }
    }

}

class TrendingAdapterViewHolder(var binding: ItemTrendingBinding) :
    RecyclerView.ViewHolder(binding.root)

data class TrendingItems(val photo: String?, val name: String?, val title: String?, val description: String?, val language: String?, val star: String?,
                         val fork: String?, var cardStatus: String = "HIDE")




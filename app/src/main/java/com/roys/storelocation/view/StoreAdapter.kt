package com.roys.storelocation.view

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roys.storelocation.util.AppTools
import com.roys.storelocation.databinding.StoreItemBinding
import com.roys.storelocation.model.StoreEntity

class StoreAdapter(private val itemList: List<StoreEntity>): RecyclerView.Adapter<StoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = StoreItemBinding.inflate(LayoutInflater.from(parent.context))
        return StoreViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(itemList[position])
    }


}

class StoreViewHolder(private val binding: StoreItemBinding, private val context: Context): RecyclerView.ViewHolder(binding.root){
    fun bind(storeEntity: StoreEntity){
        val name = "Store Name: ${storeEntity.NAME}"
        binding.storeName.text = name
        val uri = Uri.parse(storeEntity.IMAGE)
        Glide.with(context)
            .load(uri)
            .into(binding.storeImage)
        val dist = "Distance: ${AppTools().distance(storeEntity.STORELAT, storeEntity.STORELON, storeEntity.USERLAT, storeEntity.USERLON)} kilometers"
        binding.storeDistance.text = dist
    }

}

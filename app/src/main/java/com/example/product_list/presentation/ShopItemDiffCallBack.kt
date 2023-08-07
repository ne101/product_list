package com.example.product_list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.product_list.domain.ShopItem

class ShopItemDiffCallBack: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}
package com.example.product_list.domain.repository

import androidx.lifecycle.LiveData
import com.example.product_list.domain.entity.ShopItem

interface ShopListRepository {
    suspend fun getShopItem(shopItemId: Int): ShopItem

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    fun getShopList() : LiveData<List<ShopItem>>
}
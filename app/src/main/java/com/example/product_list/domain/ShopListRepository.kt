package com.example.product_list.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun getShopItem(shopItemId: Int): ShopItem

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    fun getShopList() : LiveData<List<ShopItem>>
}
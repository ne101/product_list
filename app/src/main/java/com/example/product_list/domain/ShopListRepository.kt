package com.example.product_list.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun getShopItem(shopItemId: Int): ShopItem

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopList() : LiveData<List<ShopItem>>
}
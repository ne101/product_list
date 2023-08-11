package com.example.product_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.product_list.data.ShopListRepositoryImpl
import com.example.product_list.domain.DeleteShopItemUseCase
import com.example.product_list.domain.EditShopItemUseCase
import com.example.product_list.domain.GetShopListUseCase
import com.example.product_list.domain.ShopItem


class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }


    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}
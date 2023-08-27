package com.example.product_list.presentation.view_models

import android.app.Application
import androidx.lifecycle.*
import com.example.product_list.data.repository.ShopListRepositoryImpl
import com.example.product_list.domain.usecase.DeleteShopItemUseCase
import com.example.product_list.domain.usecase.EditShopItemUseCase
import com.example.product_list.domain.usecase.GetShopListUseCase
import com.example.product_list.domain.entity.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }

    }


    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newItem)
        }
    }

}
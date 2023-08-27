package com.example.product_list.domain.usecase

import androidx.lifecycle.LiveData
import com.example.product_list.domain.entity.ShopItem
import com.example.product_list.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}
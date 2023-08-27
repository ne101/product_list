package com.example.product_list.domain.usecase

import com.example.product_list.domain.entity.ShopItem
import com.example.product_list.domain.repository.ShopListRepository
import javax.inject.Inject

class DeleteShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}
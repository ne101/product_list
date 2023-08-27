package com.example.product_list.domain.usecase

import com.example.product_list.domain.entity.ShopItem
import com.example.product_list.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}
package com.example.product_list.di

import android.app.Application
import com.example.product_list.data.database.AppDataBase
import com.example.product_list.data.database.ShopListDao
import com.example.product_list.data.repository.ShopListRepositoryImpl
import com.example.product_list.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun providesShopListDao(application: Application): ShopListDao {
            return AppDataBase.getInstance(application).shopListDao()
        }
    }
}
package com.example.product_list.presentation

import android.app.Application
import com.example.product_list.di.DaggerApplicationComponent

class ShopApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
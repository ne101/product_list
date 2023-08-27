package com.example.product_list.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.product_list.R
import com.example.product_list.domain.entity.ShopItem
import com.example.product_list.presentation.fragments.ShopItemFragment
import com.example.product_list.presentation.view_models.ShopItemViewModel
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tiName: TextInputLayout
    private lateinit var tiCount: TextInputLayout
    private lateinit var inputName: EditText
    private lateinit var inputCount: EditText
    private lateinit var saveButton: Button

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }

    }

    private fun launchRightMode() {
       val fragment =  when (screenMode) {
            EXTRA_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            EXTRA_ADD -> ShopItemFragment.newInstanceAddItem()
           else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != EXTRA_EDIT && mode != EXTRA_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == EXTRA_EDIT) {
            if (!intent.hasExtra(EXTRA_ITEM_SHOP_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_ITEM_SHOP_ID, ShopItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "EXTRA_SCREEN_MODE"
        private const val EXTRA_EDIT = "EXTRA_EDIT"
        private const val EXTRA_ADD = "EXTRA_ADD"
        private const val EXTRA_ITEM_SHOP_ID = "EXTRA_ITEM_SHOP_ID"
        private const val MODE_UNKNOWN = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, EXTRA_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, EXTRA_EDIT)
            intent.putExtra(EXTRA_ITEM_SHOP_ID, id)
            return intent
        }
    }
}
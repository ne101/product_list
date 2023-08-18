package com.example.product_list.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.product_list.R
import com.example.product_list.databinding.FragmentShopItemBinding
import com.example.product_list.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment : Fragment() {
    private lateinit var viewModel: ShopItemViewModel
    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID
    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun launchRightMode() {
        when (screenMode) {
            EXTRA_EDIT -> launchEditMode()
            EXTRA_ADD -> launchAddMode()
        }
    }

    private fun observeViewModel() {

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()

        }
    }

    private fun addTextChangeListeners() {
        binding.nameId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.countId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun launchAddMode() {
        binding.button.setOnClickListener {
            viewModel.addShopItem(binding.nameId.text?.toString(), binding.countId.text?.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        binding.button.setOnClickListener {
            viewModel.editShopItem(
                binding.nameId.text?.toString(),
                binding.countId.text?.toString()
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }

        val mode = args.getString(EXTRA_SCREEN_MODE)
        if (mode != EXTRA_EDIT && mode != EXTRA_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == EXTRA_EDIT) {
            if (!args.containsKey(EXTRA_ITEM_SHOP_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(EXTRA_ITEM_SHOP_ID, shopItemId)
        }
    }


    companion object {
        private const val EXTRA_SCREEN_MODE = "EXTRA_SCREEN_MODE"
        private const val EXTRA_EDIT = "EXTRA_EDIT"
        private const val EXTRA_ADD = "EXTRA_ADD"
        private const val EXTRA_ITEM_SHOP_ID = "EXTRA_ITEM_SHOP_ID"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, EXTRA_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, EXTRA_EDIT)
                    putInt(EXTRA_ITEM_SHOP_ID, shopItemId)
                }
            }
        }

    }


}
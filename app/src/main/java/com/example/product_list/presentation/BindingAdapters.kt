package com.example.product_list.presentation

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindingErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        "Invalid name"
    } else {
        null
    }
    textInputLayout.error = message
}
@BindingAdapter("errorInputCount")
fun bindingErrorCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        "Invalid count"
    } else {
        null
    }
    textInputLayout.error = message
}
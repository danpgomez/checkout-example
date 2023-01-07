package com.example.checkoutexample

import androidx.lifecycle.ViewModel

class ProductDetailViewModel: ViewModel() {
    private val productPrice = 5
    var orderTotalString = 0

    fun onAddButtonClicked(quantity: Int) {
        orderTotalString = calculateOrderTotal(quantity)
    }

    private fun calculateOrderTotal(quantity: Int): Int {
        return quantity * productPrice
    }
}

package com.example.checkoutexample

import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {
    private val price = 5
    var remainingStock = 5
    var orderTotal = 0
    var insufficientStock = false

    fun onAddButtonClicked(quantity: Int) {
        insufficientStock = if (quantity <= remainingStock) {
            updateStock(quantity)
            updateOrderTotal(quantity)
            false
        } else {
            true
        }
    }

    private fun updateOrderTotal(quantity: Int) {
        orderTotal += quantity * price
    }

    private fun updateStock(quantity: Int) {
        remainingStock -= quantity
    }
}

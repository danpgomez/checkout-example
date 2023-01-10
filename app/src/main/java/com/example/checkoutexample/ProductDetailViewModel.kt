package com.example.checkoutexample

import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {
    private val price = 5
    var remainingStock = 5
    var orderTotal = 0
    var errorMessage = ""
    var insufficientStock = false
    var outOfStock = false

    fun onAddButtonClicked(quantity: Int) {
        if (quantity <= remainingStock) {
            updateStock(quantity)
            updateOrderTotal(quantity)
            insufficientStock = false
        } else {
            insufficientStock = true
            generateErrorMessage()
        }
    }

    private fun updateOrderTotal(quantity: Int) {
        orderTotal += quantity * price
    }

    private fun updateStock(quantity: Int) {
        remainingStock -= quantity
        if (remainingStock == 0) outOfStock = true
    }

    private fun generateErrorMessage() {
        errorMessage = "😿 Sorry. We only have $remainingStock cupcakes."
    }
}

package com.example.checkoutexample

import androidx.lifecycle.ViewModel

class ShoppingCartViewModel(val orderTotal: Int): ViewModel() {
    fun generateCartMessage(): String {
        return if (orderTotal < 1) {
            "Your cart is empty"
        } else {
            """
                Thanks for your order!
                
                Your total is: $$orderTotal
            """.trimIndent()
        }
    }
}

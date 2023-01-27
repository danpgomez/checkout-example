package com.example.checkoutexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {
    private val price = 5

    private val _remainingStock = MutableLiveData(5)
    val remainingStock: LiveData<Int> get() = _remainingStock

    private val _orderTotal = MutableLiveData(0)
    val orderTotal: LiveData<Int> get() = _orderTotal

    private val _insufficientStock = MutableLiveData(false)
    val insufficientStock: LiveData<Boolean> get() = _insufficientStock

    fun onAddButtonClicked(quantity: Int) {
        _remainingStock.value?.let {
            _insufficientStock.value = if (quantity <= it) {
                updateStock(quantity)
                updateOrderTotal(quantity)
                false
            } else {
                true
            }
        }
    }

    private fun updateOrderTotal(quantity: Int) {
        _orderTotal.value = _orderTotal.value?.plus(quantity * price)
    }

    private fun updateStock(quantity: Int) {
        _remainingStock.value = _remainingStock.value?.minus(quantity)
    }
}

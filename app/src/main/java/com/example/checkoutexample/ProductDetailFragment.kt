package com.example.checkoutexample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import androidx.navigation.findNavController
import com.example.checkoutexample.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val productPrice = 5
    private var orderTotalString = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            checkoutButton.text = getString(R.string.checkout, orderTotalString)

            addToCartButton.setOnClickListener {
                orderTotalString = calculateOrderTotal()
                hideKeyboard(view)
                checkoutButton.text = getString(R.string.checkout, orderTotalString)
            }

            checkoutButton.setOnClickListener {
                val action = ProductDetailFragmentDirections.actionProductDetailFragmentToShoppingCartFragment(orderTotalString)
                view.findNavController().navigate(action)
            }
        }

        return view
    }

    private fun hideKeyboard(view: ScrollView) {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun calculateOrderTotal(): Int {
        val orderQuantity = binding.quantityNumber.text.toString().toInt()
        return orderQuantity * productPrice
    }

}

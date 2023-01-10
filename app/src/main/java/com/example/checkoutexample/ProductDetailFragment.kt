package com.example.checkoutexample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.checkoutexample.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]

        binding.apply {
            checkoutButton.text = getString(R.string.checkout, viewModel.orderTotal)
            stockCountMessage.text = getString(R.string.stock_msg, viewModel.remainingStock)

            addToCartButton.setOnClickListener {
                val orderQuantity = quantityNumber.text.toString().toInt()
                viewModel.onAddButtonClicked(orderQuantity)
                stockCountMessage.text = getString(R.string.stock_msg, viewModel.remainingStock)

                if (viewModel.insufficientStock) {
                    Toast.makeText(
                        context,
                        viewModel.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (viewModel.outOfStock) {
                    it.isEnabled = false
                    stockCountMessage.text = getString(R.string.out_of_stock)
                    stockCountMessage.setTextColor(Color.RED)
                }

                hideKeyboard(view)
                checkoutButton.text = getString(R.string.checkout, viewModel.orderTotal)
            }

            checkoutButton.setOnClickListener {
                val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToShoppingCartFragment(
                        viewModel.orderTotal
                    )
                view.findNavController().navigate(action)
            }
        }

        return view
    }

    private fun hideKeyboard(view: ScrollView) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

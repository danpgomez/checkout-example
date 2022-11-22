package com.example.checkoutexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.checkoutexample.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            val orderTotal = ShoppingCartFragmentArgs.fromBundle(requireArguments()).orderTotal
            orderTotalAmount.text = getString(R.string.order_total_cost, orderTotal)

            editOrderButton.setOnClickListener {
                val action = ShoppingCartFragmentDirections.actionShoppingCartFragmentToProductDetailFragment()
                view.findNavController().navigate(action)
            }
        }
        return view
    }

}

package com.example.checkoutexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.checkoutexample.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoppingCartViewModel
    private lateinit var viewModelFactory: ShoppingCartViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root
        val orderTotal = ShoppingCartFragmentArgs.fromBundle(requireArguments()).orderTotal
        viewModelFactory = ShoppingCartViewModelFactory(orderTotal)
        viewModel = ViewModelProvider(this, viewModelFactory)[ShoppingCartViewModel::class.java]

        binding.apply {
            orderTotalAmount.text = viewModel.generateCartMessage()

            editOrderButton.setOnClickListener {
                val action = ShoppingCartFragmentDirections.actionShoppingCartFragmentToProductDetailFragment()
                view.findNavController().navigate(action)
            }
        }
        return view
    }

}

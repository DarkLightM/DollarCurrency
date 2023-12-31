package com.example.currencytask.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencytask.databinding.FragmentHomeBinding
import androidx.fragment.app.viewModels
import com.example.currencytask.MainApplication
import com.example.currencytask.di.ViewModelFactory
import com.example.currencytask.models.Currency
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewModel by viewModels<HomeViewModel> { viewModelFactory }
    private lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedCurrencyValue = binding.savedCurrencyValue
        homeViewModel.defaultCurrency.observe(viewLifecycleOwner){
            savedCurrencyValue.setText(it)
        }

        savedCurrencyValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputValue = s.toString()
                homeViewModel.setDefaultCurrency(inputValue)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        requireContext().startForegroundService(Intent(context, NotificationService::class.java))

        homeViewModel.currencyList.observe(viewLifecycleOwner) {
            setCurrency(it.records?.reversed())
        }
    }

    private fun setCurrency(currencyList: List<Currency>?) {
        activity?.runOnUiThread {
            val currencyRecycler = binding.currencyList
            val currencyCardAdapter = CurrencyCardAdapter()
            currencyCardAdapter.submitList(currencyList)
            currencyRecycler.adapter = currencyCardAdapter
        }
    }
}
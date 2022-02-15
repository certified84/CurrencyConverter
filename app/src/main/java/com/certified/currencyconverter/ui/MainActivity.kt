package com.certified.currencyconverter.ui

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.certified.currencyconverter.R
import com.certified.currencyconverter.databinding.ActivityMainBinding
import com.certified.currencyconverter.util.Config.ACCESS_KEY
import com.certified.currencyconverter.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            uiState = viewModel.uiState
            lifecycleOwner = this@MainActivity
            viewModel.conversionResponse.observe(this@MainActivity) {
                if (it != null) {
                    etTo.setText(it.result.toString())
                }
            }
            btnConvert.setOnClickListener {

                val amount = etFrom.text.toString()
                val fromCurrency = etFromDropDown.text.toString()
                val toCurrency = etToDropDown.text.toString()

                if (amount.isBlank()) {
                    etFrom.error = "*Required"
                    etFromLayout.requestFocus()
                    return@setOnClickListener
                }

                if (fromCurrency.isBlank()) {
                    etFromDropDown.error = "*Required"
                    etFromDropDownLayout.requestFocus()
                    return@setOnClickListener
                }

                if (toCurrency.isBlank()) {
                    etToDropDown.error = "*Required"
                    etToDropDownLayout.requestFocus()
                    return@setOnClickListener
                }

                etFrom.error = null
                etFromDropDown.error = null
                etToDropDown.error = null

                viewModel.uiState.set(UIState.LOADING)
                convert(ACCESS_KEY, fromCurrency, toCurrency, amount.toInt())
            }
        }
    }

    private fun convert(access_key: String, from: String, to: String, amount: Int) {
        viewModel.convert(access_key, from, to, amount)
    }

    override fun onResume() {
        super.onResume()

        val currencies = resources.getStringArray(R.array.currencies)
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            currencies
        )
        binding.apply {

            etTo.keyListener = null
            etToDropDown.setAdapter(arrayAdapter)
            etFromDropDown.setAdapter(arrayAdapter)

            etFromDropDown.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    tvFrom.text = arrayAdapter.getItem(position)
                }

            etToDropDown.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, _, _ -> tvTo.text = etToDropDown.text }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
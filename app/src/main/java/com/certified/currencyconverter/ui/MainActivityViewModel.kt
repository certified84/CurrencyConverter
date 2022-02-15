package com.certified.currencyconverter.ui

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certified.currencyconverter.data.model.ConversionResponse
import com.certified.currencyconverter.data.repository.Repository
import com.certified.currencyconverter.util.ApiErrorUtil
import com.certified.currencyconverter.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository,
    private val apiErrorUtil: ApiErrorUtil
) : ViewModel() {

    val uiState = ObservableField(UIState.EMPTY)

    private val _conversionResponse = MutableLiveData<ConversionResponse>()
    val conversionResponse: LiveData<ConversionResponse> get() = _conversionResponse

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    fun convert(access_key: String, from: String, to: String, amount: Int) {
        viewModelScope.launch {
            try {
                val response = repository.convert(access_key, from, to, amount)
                _success.value = response.isSuccessful
                if (response.isSuccessful) {
                    uiState.set(UIState.SUCCESS)
                    _conversionResponse.value = response.body()
                    Log.d("TAG", "convert: conversionResponse: ${response.body()}")
                } else {
                    uiState.set(UIState.FAILURE)
                    val error = apiErrorUtil.parseError(response)
                    Log.d("TAG", "convert: error: ${error?.error}")
                }
            } catch (t: Throwable) {
                uiState.set(UIState.FAILURE)
                t.printStackTrace()
            }
        }
    }
}
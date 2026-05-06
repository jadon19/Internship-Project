package com.example.kasthakala.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedEstimatorViewModel : ViewModel() {

    private val _estimatedAmount =
        MutableStateFlow("")

    val estimatedAmount: StateFlow<String> =
        _estimatedAmount

    fun updateAmount(amount: String) {

        _estimatedAmount.value = amount
    }
}
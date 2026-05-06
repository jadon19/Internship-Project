package com.example.kasthakala.quotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasthakala.data.QuoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotationViewModel @Inject constructor(
    private val quoteDao: QuoteDao
) : ViewModel() {
    val savedQuotes = quoteDao.getAllQuotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveQuote(name: String, price: Double) {
        viewModelScope.launch {
            quoteDao.insertQuote(QuoteEntity(customerName = name, amount = price))
        }
    }
}
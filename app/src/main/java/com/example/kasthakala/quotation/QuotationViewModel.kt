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
    private val dao: QuoteDao
) : ViewModel() {

    val quotes = dao
        .getAllQuotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addQuote(quote: QuoteEntity) {

        viewModelScope.launch {
            dao.insertQuote(quote)
        }
    }
}
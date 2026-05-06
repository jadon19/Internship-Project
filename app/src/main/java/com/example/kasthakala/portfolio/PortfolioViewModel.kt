package com.example.kasthakala.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.kasthakala.data.PortfolioDao

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val dao: PortfolioDao
) : ViewModel() {

    val portfolioItems = dao
        .getPortfolioItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addProject(project: PortfolioEntity) {

        viewModelScope.launch {
            dao.insertProject(project)
        }
    }
}
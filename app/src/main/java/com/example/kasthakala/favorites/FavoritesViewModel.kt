package com.example.kasthakala.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.kasthakala.data.FavoriteDao

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dao: FavoriteDao
) : ViewModel() {

    val favorites = dao
        .getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addFavorite(item: FavoriteEntity) {

        viewModelScope.launch {
            dao.addFavorite(item)
        }
    }

    fun removeFavorite(item: FavoriteEntity) {

        viewModelScope.launch {
            dao.removeFavorite(item)
        }
    }
}
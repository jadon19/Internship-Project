package com.example.kasthakala.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasthakala.data.FavoriteDao
import com.example.kasthakala.favorites.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val favoriteDao: FavoriteDao
) : ViewModel() {
    fun toggleFavorite(id: String, title: String) {
        viewModelScope.launch {
            favoriteDao.addFavorite(FavoriteEntity(id, title, ""))
        }
    }
}
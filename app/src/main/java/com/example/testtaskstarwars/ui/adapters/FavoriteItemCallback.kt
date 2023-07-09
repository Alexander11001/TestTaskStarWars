package com.example.testtaskstarwars.ui.adapters

import kotlinx.coroutines.flow.Flow

interface FavoriteItemCallback {

    fun onFavoriteChanged(item: MainPageItem)

    fun isItemFavorite(item: MainPageItem): Flow<Boolean>
}
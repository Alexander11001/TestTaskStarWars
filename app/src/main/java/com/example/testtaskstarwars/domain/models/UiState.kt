package com.example.testtaskstarwars.domain.models

import com.example.testtaskstarwars.ui.adapters.MainPageItem

sealed class UiState {
    object Loading : UiState()
    data class Success(val dataList: List<MainPageItem>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}

package com.example.testtaskstarwars.domain.models

sealed class UiState {
    object Loading : UiState()
    data class Success(val dataList: List<Any>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}

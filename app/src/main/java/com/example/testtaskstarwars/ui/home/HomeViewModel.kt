package com.example.testtaskstarwars.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskstarwars.data.dto.toMainPageItemList
import com.example.testtaskstarwars.data.repository.NetworkRepositoryImpl
import com.example.testtaskstarwars.domain.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val networkRepository: NetworkRepositoryImpl) :
    ViewModel() {

//    private val _people = MutableStateFlow<List<People>?>(null)
//    val people: StateFlow<List<People>?> = _people
//
//    private val _people1 = MutableStateFlow<List<People>?>(null)
//    val people1: StateFlow<List<People>?> = _people1
//
//    fun getPeople() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val peopleList = networkRepository.loadPeople().first()
//            _people.emit(peopleList)
//        }
//    }

    val getPeople: StateFlow<UiState> = networkRepository.loadPeople()
        .map {
            if (it.isEmpty()) {
                UiState.Error("Network Error")
            } else {
                UiState.Success(it.toMainPageItemList())
            }
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState.Loading
        )
}

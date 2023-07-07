package com.example.testtaskstarwars.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskstarwars.data.dto.toMainPageItemList
import com.example.testtaskstarwars.data.repository.NetworkRepositoryImpl
import com.example.testtaskstarwars.domain.DEBOUNCE
import com.example.testtaskstarwars.domain.QUERY_LENGTH
import com.example.testtaskstarwars.domain.STOP_SUBSCRIBE_TIME
import com.example.testtaskstarwars.domain.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val networkRepository: NetworkRepositoryImpl) :
    ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val getPeopleBySearch: StateFlow<UiState> = searchQuery
        .filter { query ->
            return@filter query.length >= QUERY_LENGTH
        }
        .debounce(DEBOUNCE)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                emit(UiState.Loading)
                val result = networkRepository.loadPeopleBySearch(query).first()
                if (result.isEmpty()) {
                    emit(UiState.Error("No results found for your request"))
                } else {
                    emit(UiState.Success(result.toMainPageItemList()))
                }
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SUBSCRIBE_TIME),
            initialValue = UiState.Success(emptyList())
        )

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}


//val getPeople: StateFlow<UiState> = networkRepository.loadPeople()
//    .map {
//        if (it.isEmpty()) {
//            UiState.Error("Network Error")
//        } else {
//            UiState.Success(it.toMainPageItemList())
//        }
//    }
//    .stateIn(
//        viewModelScope,
//        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
//        initialValue = UiState.Loading
//    )

//    val getPeopleBySearch1: StateFlow<UiState> = searchQuery
//        .debounce(500)  // Add debounce to reduce the number of network requests
//        .distinctUntilChanged() // Ensure we only make a network request if the query has changed
//        .flatMapLatest { query ->
//            if (query.length < 2) {
//                networkRepository.loadPeopleBySearch(query)
//            } else {
//                flow<List<People>> { emit(emptyList()) }
//            }
//        }
//        .map {
//            if (it.isEmpty()) {
//                UiState.Error("Network Error")
//            } else {
//                UiState.Success(it.toMainPageItemList())
//            }
//        }
//        .stateIn(
//            viewModelScope,
//            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
//            initialValue = UiState.Loading
//        )


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
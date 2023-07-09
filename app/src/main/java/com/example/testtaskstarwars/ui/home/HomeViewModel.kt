package com.example.testtaskstarwars.ui.home

import androidx.lifecycle.viewModelScope
import com.example.testtaskstarwars.data.repository.DataBaseRepositoryImpl
import com.example.testtaskstarwars.data.repository.NetworkRepositoryImpl
import com.example.testtaskstarwars.domain.DEBOUNCE
import com.example.testtaskstarwars.domain.QUERY_LENGTH
import com.example.testtaskstarwars.domain.STOP_SUBSCRIBE_TIME
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.models.Planet
import com.example.testtaskstarwars.domain.models.Starships
import com.example.testtaskstarwars.domain.models.UiState
import com.example.testtaskstarwars.ui.adapters.FavoriteItemCallback
import com.example.testtaskstarwars.ui.adapters.MainPageItem
import com.example.testtaskstarwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepositoryImpl,
    dataBaseRepository: DataBaseRepositoryImpl
) :
    BaseViewModel(dataBaseRepository), FavoriteItemCallback {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val getItemsBySearch: StateFlow<UiState> = searchQuery
        .filter { query -> query.length >= QUERY_LENGTH }
        .debounce(DEBOUNCE)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            flow {
                emit(UiState.Loading)
                coroutineScope {
                    val peopleFlow = async { networkRepository.loadPeopleBySearch(query) }
                    val starshipsFlow = async { networkRepository.loadStarshipsBySearch(query) }
                    val planetFlow = async { networkRepository.loadPlanetBySearch(query) }

                    val peopleResult = peopleFlow.await().last()
                    val starshipsResult = starshipsFlow.await().last()
                    val planetResult = planetFlow.await().last()

                    val result = processSearchResults(peopleResult, starshipsResult, planetResult)

                    emit(result)
                }
            }
        }
        .flowOn(Dispatchers.IO)
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_SUBSCRIBE_TIME),
            initialValue = UiState.Success(emptyList())
        )

    private fun processSearchResults(
        peopleResult: List<People>,
        starshipsResult: List<Starships>,
        planetResult: List<Planet>
    ): UiState {
        return if (peopleResult.isEmpty() && starshipsResult.isEmpty() && planetResult.isEmpty()) {
            UiState.Error("No results found for your request")
        } else {
            val combinedList = peopleResult.map { MainPageItem.PeopleItem(it) } +
                    starshipsResult.map { MainPageItem.StarshipsItem(it) } +
                    planetResult.map { MainPageItem.PlanetItem(it) }
            UiState.Success(combinedList)
        }
    }
}


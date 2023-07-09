package com.example.testtaskstarwars.ui.favorites

import androidx.lifecycle.viewModelScope
import com.example.testtaskstarwars.data.repository.DataBaseRepositoryImpl
import com.example.testtaskstarwars.data.room.entities.toPeople
import com.example.testtaskstarwars.data.room.entities.toPlanet
import com.example.testtaskstarwars.data.room.entities.toStarships
import com.example.testtaskstarwars.domain.models.UiState
import com.example.testtaskstarwars.domain.repository.DataBaseRepository
import com.example.testtaskstarwars.ui.adapters.FavoriteItemCallback
import com.example.testtaskstarwars.ui.adapters.MainPageItem
import com.example.testtaskstarwars.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(dataBaseRepository: DataBaseRepository) :
    FavoriteItemCallback, BaseViewModel(dataBaseRepository) {

    private val _uiState = dataBaseRepository.getAllPeople()
        .combine(dataBaseRepository.getAllStarships()) { people, starships -> people to starships }
        .combine(dataBaseRepository.getAllPlanets()) { (people, starships), planets ->
            Triple(
                people,
                starships,
                planets
            )
        }
        .map { (people, starships, planets) ->

            val peopleItems = people.map { MainPageItem.PeopleItem(it.toPeople()) }
            val starshipsItems = starships.map { MainPageItem.StarshipsItem(it.toStarships()) }
            val planetItems = planets.map { MainPageItem.PlanetItem(it.toPlanet()) }

            val items = mutableListOf<MainPageItem>()
            items.addAll(peopleItems)
            items.addAll(starshipsItems)
            items.addAll(planetItems)

            //cast is needed because of the type erasure, when I remove it there is an type mismatch error
            UiState.Success(items) as UiState
        }
        .catch { e -> emit(UiState.Error(e.message ?: "Unknown Error")) }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading)

    val uiState: StateFlow<UiState> = _uiState
}


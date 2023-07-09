package com.example.testtaskstarwars.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskstarwars.data.room.entities.toEntity
import com.example.testtaskstarwars.domain.repository.DataBaseRepository
import com.example.testtaskstarwars.ui.adapters.FavoriteItemCallback
import com.example.testtaskstarwars.ui.adapters.MainPageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val dataBaseRepository: DataBaseRepository
) : ViewModel(), FavoriteItemCallback {

    override fun onFavoriteChanged(item: MainPageItem) {
        when (item) {
            is MainPageItem.PeopleItem -> handleFavoriteChange(
                item,
                this::updateFavoritePeopleStatus
            )

            is MainPageItem.StarshipsItem -> handleFavoriteChange(
                item,
                this::updateFavoriteStarshipStatus
            )

            is MainPageItem.PlanetItem -> handleFavoriteChange(
                item,
                this::updateFavoritePlanetStatus
            )
        }
    }

    private fun <T : MainPageItem> handleFavoriteChange(
        item: T,
        updateFunction: (T) -> Flow<Result<Unit>>
    ) {
        viewModelScope.launch {
            updateFunction(item).collect { result ->
                if (result.isSuccess) {
                    Log.e("HomeViewModel", "Successful update of favorite status")
                } else if (result.isFailure) {
                    Log.e(
                        "HomeViewModel",
                        "Error while updating favorite status " + result.exceptionOrNull()
                            .toString()
                    )
                }
            }
        }
    }


    private fun updateFavoritePeopleStatus(item: MainPageItem.PeopleItem): Flow<Result<Unit>> =
        flow {
            val isPresent = dataBaseRepository.containsPeople(item.people.name).first()
            if (isPresent) {
                val peopleEntityToDelete =
                    dataBaseRepository.getPeopleByName(item.people.name).first().firstOrNull()
                if (peopleEntityToDelete != null) {
                    dataBaseRepository.deletePeople(peopleEntityToDelete)
                }
            } else {
                dataBaseRepository.insertPeople(item.people.toEntity())
            }
            emit(Result.success(Unit))
        }
            .flowOn(Dispatchers.IO)
            .catch { e -> emit(Result.failure(e)) }


    private fun updateFavoriteStarshipStatus(item: MainPageItem.StarshipsItem): Flow<Result<Unit>> =
        flow {
            val isPresent = dataBaseRepository.containsStarships(item.starships.name).first()
            if (isPresent) {
                val starshipEntityToDelete =
                    dataBaseRepository.getStarshipsByName(item.starships.name).first().firstOrNull()
                if (starshipEntityToDelete != null) {
                    dataBaseRepository.deleteStarship(starshipEntityToDelete)
                }
            } else {
                dataBaseRepository.insertStarship(item.starships.toEntity())
            }
            emit(Result.success(Unit))
        }
            .flowOn(Dispatchers.IO)
            .catch { e -> emit(Result.failure(e)) }

    private fun updateFavoritePlanetStatus(item: MainPageItem.PlanetItem): Flow<Result<Unit>> =
        flow {
            val isPresent = dataBaseRepository.containsPlanet(item.planet.name).first()
            if (isPresent) {
                val planetEntityToDelete =
                    dataBaseRepository.getPlanetByName(item.planet.name).first().firstOrNull()
                if (planetEntityToDelete != null) {
                    dataBaseRepository.deletePlanet(planetEntityToDelete)
                }
            } else {
                dataBaseRepository.insertPlanet(item.planet.toEntity())
            }
            emit(Result.success(Unit))
        }
            .flowOn(Dispatchers.IO)
            .catch { e -> emit(Result.failure(e)) }


    override fun isItemFavorite(item: MainPageItem): Flow<Boolean> {
        return when (item) {
            is MainPageItem.PeopleItem -> containsPeople(item.people.name)

            is MainPageItem.StarshipsItem -> containsStarships(item.starships.name)

            is MainPageItem.PlanetItem -> containsPlanets(item.planet.name)
        }
    }

    private fun containsPeople(name: String): Flow<Boolean> =
        dataBaseRepository.containsPeople(name)
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)

    private fun containsPlanets(name: String): Flow<Boolean> =
        dataBaseRepository.containsPlanet(name)
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)

    private fun containsStarships(name: String): Flow<Boolean> =
        dataBaseRepository.containsStarships(name)
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
}




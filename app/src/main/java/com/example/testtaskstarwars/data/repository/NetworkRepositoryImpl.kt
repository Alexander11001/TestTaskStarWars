package com.example.testtaskstarwars.data.repository

import com.example.testtaskstarwars.data.api.StarWarsApi
import com.example.testtaskstarwars.data.dto.toDataPeople
import com.example.testtaskstarwars.data.dto.toDataPlanetList
import com.example.testtaskstarwars.data.dto.toDataStarshipsList
import com.example.testtaskstarwars.data.dto.toPeopleList
import com.example.testtaskstarwars.data.dto.toPlanetList
import com.example.testtaskstarwars.data.dto.toStarshipList
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.models.Planet
import com.example.testtaskstarwars.domain.models.Starships
import com.example.testtaskstarwars.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: StarWarsApi) : NetworkRepository {

    override fun loadPeople(): Flow<List<People>> = flow {
        val dtoList = api.getPeople().toDataPeople()
        emit(dtoList.toPeopleList())
    }

    override fun loadPeopleBySearch(name: String): Flow<List<People>> = flow {
        val dtoList = api.getPeopleBySearch(name).toDataPeople()
        emit(dtoList.toPeopleList())
    }

    override fun loadStarshipsBySearch(name: String): Flow<List<Starships>> = flow {
        val dtoList = api.getStarshipsBySearch(name).toDataStarshipsList()
        emit(dtoList.toStarshipList())
    }

    override fun loadPlanetBySearch(name: String): Flow<List<Planet>> = flow {
        val dtoList = api.getPlanetsBySearch(name).toDataPlanetList()
        emit(dtoList.toPlanetList())
    }
}

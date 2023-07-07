package com.example.testtaskstarwars.domain.repository

import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.models.Planet
import com.example.testtaskstarwars.domain.models.Starships
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

     fun loadPeople(): Flow<List<People>>

     fun loadPeopleBySearch(name: String): Flow<List<People>>

     fun loadStarshipsBySearch(name: String): Flow<List<Starships>>

     fun loadPlanetBySearch(name: String): Flow<List<Planet>>

}
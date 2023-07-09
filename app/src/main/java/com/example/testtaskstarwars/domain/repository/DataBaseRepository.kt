package com.example.testtaskstarwars.domain.repository

import com.example.testtaskstarwars.data.room.entities.FilmsEntity
import com.example.testtaskstarwars.data.room.entities.PeopleEntity
import com.example.testtaskstarwars.data.room.entities.PlanetEntity
import com.example.testtaskstarwars.data.room.entities.StarshipsEntity
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {

    fun getAllPeople(): Flow<List<PeopleEntity>>

    fun getPeopleByName(name: String): Flow<List<PeopleEntity>>

    fun getAllStarships(): Flow<List<StarshipsEntity>>

    fun getStarshipsByName(name: String): Flow<List<StarshipsEntity>>

    fun getAllPlanets(): Flow<List<PlanetEntity>>

    fun getPlanetByName(name: String): Flow<List<PlanetEntity>>

    fun getAllFilms(): Flow<List<FilmsEntity>>

    fun getFilmByTitle(title: String): Flow<List<FilmsEntity>>

    suspend fun insertPeople(people: PeopleEntity)

    suspend fun deletePeople(people: PeopleEntity)

    suspend fun insertStarship(starship: StarshipsEntity)

    suspend fun deleteStarship(starship: StarshipsEntity)

    suspend fun insertPlanet(planet: PlanetEntity)

    suspend fun deletePlanet(planet: PlanetEntity)

    suspend fun insertFilm(film: FilmsEntity)

    suspend fun deleteFilm(film: FilmsEntity)

    fun containsPeople(name: String): Flow<Boolean>
    fun containsPlanet(name: String): Flow<Boolean>
    fun containsStarships(name: String): Flow<Boolean>

}

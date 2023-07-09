package com.example.testtaskstarwars.data.repository

import com.example.testtaskstarwars.data.room.dao.StarWarsDao
import com.example.testtaskstarwars.data.room.entities.FilmsEntity
import com.example.testtaskstarwars.data.room.entities.PeopleEntity
import com.example.testtaskstarwars.data.room.entities.PlanetEntity
import com.example.testtaskstarwars.data.room.entities.StarshipsEntity
import com.example.testtaskstarwars.domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(private val dao: StarWarsDao) :
    DataBaseRepository {

    override fun getAllPeople(): Flow<List<PeopleEntity>> = dao.getAllPeople()

    override fun getPeopleByName(name: String): Flow<List<PeopleEntity>> = dao.getPeopleByName(name)

    override fun getAllStarships(): Flow<List<StarshipsEntity>> = dao.getAllStarships()

    override fun getStarshipsByName(name: String): Flow<List<StarshipsEntity>> =
        dao.getStarshipsByName(name)

    override fun getAllPlanets(): Flow<List<PlanetEntity>> = dao.getAllPlanets()

    override fun getPlanetByName(name: String): Flow<List<PlanetEntity>> = dao.getPlanetByName(name)

    override fun getAllFilms(): Flow<List<FilmsEntity>> = dao.getAllFilms()

    override fun getFilmByTitle(title: String): Flow<List<FilmsEntity>> = dao.getFilmByTitle(title)

    override suspend fun insertPeople(people: PeopleEntity) = dao.insertPeople(people)

    override suspend fun deletePeople(people: PeopleEntity) = dao.deletePeople(people)

    override suspend fun insertStarship(starship: StarshipsEntity) = dao.insertStarship(starship)

    override suspend fun deleteStarship(starship: StarshipsEntity) = dao.deleteStarship(starship)

    override suspend fun insertPlanet(planet: PlanetEntity) = dao.insertPlanet(planet)

    override suspend fun deletePlanet(planet: PlanetEntity) = dao.deletePlanet(planet)

    override suspend fun insertFilm(film: FilmsEntity) = dao.insertFilm(film)

    override suspend fun deleteFilm(film: FilmsEntity) = dao.deleteFilm(film)

    override fun containsPeople(name: String): Flow<Boolean> = dao.containsPeople(name)

    override fun containsPlanet(name: String): Flow<Boolean> = dao.containsPlanet(name)

    override fun containsStarships(name: String): Flow<Boolean> = dao.containsStarships(name)
}

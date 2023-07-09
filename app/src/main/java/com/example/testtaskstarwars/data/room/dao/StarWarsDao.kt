package com.example.testtaskstarwars.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testtaskstarwars.data.room.entities.FilmsEntity
import com.example.testtaskstarwars.data.room.entities.PeopleEntity
import com.example.testtaskstarwars.data.room.entities.PlanetEntity
import com.example.testtaskstarwars.data.room.entities.StarshipsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarWarsDao {
    // For Films
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmsEntity)

    @Delete
    suspend fun deleteFilm(film: FilmsEntity)

    @Query("SELECT * FROM filmsentity")
    fun getAllFilms(): Flow<List<FilmsEntity>>

    @Query("SELECT * FROM filmsentity WHERE title LIKE :title")
    fun getFilmByTitle(title: String): Flow<List<FilmsEntity>>

    // For People
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: PeopleEntity)

    @Delete
    suspend fun deletePeople(people: PeopleEntity)

    @Query("SELECT * FROM peopleentity")
    fun getAllPeople(): Flow<List<PeopleEntity>>

    @Query("SELECT * FROM peopleentity WHERE name LIKE :name")
    fun getPeopleByName(name: String): Flow<List<PeopleEntity>>

    // For Starships
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starship: StarshipsEntity)

    @Delete
    suspend fun deleteStarship(starship: StarshipsEntity)

    @Query("SELECT * FROM starshipsentity")
    fun getAllStarships(): Flow<List<StarshipsEntity>>

    @Query("SELECT * FROM starshipsentity WHERE name LIKE :name")
    fun getStarshipsByName(name: String): Flow<List<StarshipsEntity>>

    // For Planets
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity)

    @Delete
    suspend fun deletePlanet(planet: PlanetEntity)

    @Query("SELECT * FROM planetentity")
    fun getAllPlanets(): Flow<List<PlanetEntity>>

    @Query("SELECT * FROM planetentity WHERE name LIKE :name")
    fun getPlanetByName(name: String): Flow<List<PlanetEntity>>

    //contains block
    @Query("SELECT EXISTS(SELECT 1 FROM peopleentity WHERE name LIKE :name LIMIT 1)")
    fun containsPeople(name: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM planetentity WHERE name LIKE :name LIMIT 1)")
    fun containsPlanet(name: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM starshipsentity WHERE name LIKE :name LIMIT 1)")
    fun containsStarships(name: String): Flow<Boolean>
}


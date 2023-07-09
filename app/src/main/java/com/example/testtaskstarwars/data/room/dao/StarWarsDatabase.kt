package com.example.testtaskstarwars.data.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtaskstarwars.data.room.entities.FilmsEntity
import com.example.testtaskstarwars.data.room.entities.PeopleEntity
import com.example.testtaskstarwars.data.room.entities.PeopleFilmCrossRef
import com.example.testtaskstarwars.data.room.entities.PlanetEntity
import com.example.testtaskstarwars.data.room.entities.StarshipFilmCrossRef
import com.example.testtaskstarwars.data.room.entities.StarshipsEntity

@Database(
    entities = [FilmsEntity::class, PeopleEntity::class,
        PeopleFilmCrossRef::class, PlanetEntity::class,
        StarshipsEntity::class, StarshipFilmCrossRef::class],
    version = 1
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun starWarsDao(): StarWarsDao
}
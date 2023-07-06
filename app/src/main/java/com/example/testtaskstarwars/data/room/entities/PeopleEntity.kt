package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeopleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val gender: String,
    val starships: Int,
)

@Entity(primaryKeys = ["starshipId", "filmId"])
data class PeopleFilmCrossRef(
    val people: Int,
    val filmId: Int
)
package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarshipsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
)

@Entity(primaryKeys = ["starshipId", "filmId"])
data class StarshipFilmCrossRef(
    val starshipId: Int,
    val filmId: Int
)
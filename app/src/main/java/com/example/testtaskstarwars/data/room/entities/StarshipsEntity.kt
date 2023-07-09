package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtaskstarwars.domain.models.Starships

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

fun Starships.toEntity(): StarshipsEntity {
    return StarshipsEntity(
        id = 0,
        name = this.name,
        model = this.model,
        manufacturer = this.manufacturer,
        passengers = this.passengers
    )
}

fun StarshipsEntity.toStarships(): Starships {
    return Starships(
        name = this.name,
        model = this.model,
        manufacturer = this.manufacturer,
        passengers = this.passengers,
        films = listOf()
    )
}
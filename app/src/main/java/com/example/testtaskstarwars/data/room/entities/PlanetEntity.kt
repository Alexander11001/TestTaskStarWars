package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtaskstarwars.domain.models.Planet

@Entity
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val diameter: String,
    val population: String
)

fun Planet.toEntity(): PlanetEntity {
    return PlanetEntity(
        id = 0,
        name = this.name,
        diameter = this.diameter,
        population = this.population
    )
}

fun PlanetEntity.toPlanet(): Planet {
    return Planet(
        name = this.name,
        diameter = this.diameter,
        population = this.population
    )
}
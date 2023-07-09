package com.example.testtaskstarwars.domain.models

data class Planet(
    val name: String,
    val diameter: String,
    val population: String,
    var isFavorite: Boolean = false
)

data class DataPlanet(
    val results: List<Planet>
)

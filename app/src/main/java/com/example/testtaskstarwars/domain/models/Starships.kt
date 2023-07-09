package com.example.testtaskstarwars.domain.models

// «имя, модель, производитель, пассажиры» для звездолетов.



data class Starships(
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
    var isFavorite: Boolean = false
)

data class DataStarships(
    val results: List<Starships>
)

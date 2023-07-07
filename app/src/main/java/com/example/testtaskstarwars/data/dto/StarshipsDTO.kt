package com.example.testtaskstarwars.data.dto

import com.example.testtaskstarwars.domain.models.DataPeople
import com.example.testtaskstarwars.domain.models.DataStarships
import com.example.testtaskstarwars.domain.models.Films
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.models.Starships
import com.google.gson.annotations.SerializedName

data class StarshipsDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("manufacturer")
    val manufacturer: String?,
    @SerializedName("passengers")
    val passengers: String?,
    @SerializedName("films")
    val films: List<String>?
)

data class DataStarshipsDTO(
    @SerializedName("results")
    val results: List<StarshipsDTO>?
)

fun DataStarshipsDTO.toDataStarshipsList(): DataStarships {
    return DataStarships(
        results = this.results?.map { it.toStarships() } ?: emptyList()
    )
}

fun StarshipsDTO.toStarships() = Starships(
    name = this.name ?: "",
    model = this.model ?: "",
    manufacturer = this.manufacturer ?: "",
    passengers = this.passengers ?: "",
    films = this.films ?: emptyList()
)

fun DataStarships.toStarshipList(): List<Starships> {
    return this.results
}



package com.example.testtaskstarwars.data.dto

import com.example.testtaskstarwars.domain.models.DataPlanet
import com.example.testtaskstarwars.domain.models.Planet
import com.example.testtaskstarwars.ui.adapters.MainPageItem
import com.google.gson.annotations.SerializedName

data class PlanetDTO(
    @SerializedName("name")
    val name: String?,

    @SerializedName("diameter")
    val diameter: String?,

    @SerializedName("population")
    val population: String?
)

data class DataPlanetsDTO(
    @SerializedName("results")
    val results: List<PlanetDTO>?
)

fun DataPlanetsDTO.toDataPlanetList(): DataPlanet {
    return DataPlanet(
        results = this.results?.map { it.toPlanets() } ?: emptyList()
    )
}

fun PlanetDTO.toPlanets() = Planet(
    name = this.name ?: "",
    diameter = this.diameter ?: "",
    population = this.population ?: "",
)

fun DataPlanet.toPlanetList(): List<Planet> {
    return this.results
}

fun List<Planet>.toMainPageItemList(): List<MainPageItem> {
    return this.map { it.toMainPageItem() }
}

fun Planet.toMainPageItem(): MainPageItem {
    return MainPageItem.PlanetItem(this)
}
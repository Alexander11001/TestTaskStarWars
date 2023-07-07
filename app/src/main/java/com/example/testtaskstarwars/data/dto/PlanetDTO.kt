package com.example.testtaskstarwars.data.dto

import com.google.gson.annotations.SerializedName

data class PlanetDTO (
    @SerializedName("name")
    val name: String?,

    @SerializedName("diameter")
    val diameter: String?,

    @SerializedName("population")
    val population: String?
)

data class DataPlanetDTO(
    @SerializedName("results")
    val results: List<PlanetDTO>?
)

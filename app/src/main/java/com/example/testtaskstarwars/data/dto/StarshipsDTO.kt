package com.example.testtaskstarwars.data.dto

import com.example.testtaskstarwars.domain.models.Films
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
    val films: List<Films>?
)

data class DataStarshipsDTO(
    @SerializedName("results")
    val results: List<StarshipsDTO>?
)

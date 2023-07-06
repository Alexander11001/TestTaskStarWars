package com.example.testtaskstarwars.data.dto

import com.example.testtaskstarwars.domain.models.Films
import com.google.gson.annotations.SerializedName

data class PeopleDTO (
    @SerializedName("name")
    val name: String?,
    @SerializedName("gender")
    val gender: String?,
    //Only quantity of starships required
    @SerializedName("starships")
    val starships: List<String>?,
    @SerializedName("films")
    val films: List<Films>?
)

data class DataPeopleDTO(
    @SerializedName("results")
    val results: List<PeopleDTO>?
)
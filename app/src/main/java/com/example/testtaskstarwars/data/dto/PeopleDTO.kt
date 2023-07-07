package com.example.testtaskstarwars.data.dto

import com.example.testtaskstarwars.domain.models.DataPeople
import com.example.testtaskstarwars.domain.models.Films
import com.example.testtaskstarwars.domain.models.People
import com.google.gson.annotations.SerializedName

data class PeopleDTO(
    @SerializedName("name")
    val name: String?,
    @SerializedName("gender")
    val gender: String?,
    //Only quantity of starships required
    @SerializedName("starships")
    val starships: List<String>?,
    @SerializedName("films")
    val films: List<String>?
)

data class DataPeopleDTO(
    @SerializedName("results")
    val results: List<PeopleDTO>?
)

fun DataPeopleDTO.toPeopleList(): List<People> {
    return this.results?.map { it.toPeople() } ?: emptyList()
}

fun DataPeople.toPeopleList(): List<People> {
    return this.results
}

fun DataPeopleDTO.toDataPeople(): DataPeople {
    return DataPeople(
        results = this.results?.map { it.toPeople() } ?: emptyList()
    )
}

fun PeopleDTO.toPeople() = People(
    name = this.name ?: "",
    gender = this.gender ?: "",
    starships = this.starships?.size ?: 0,
    films = this.films ?: emptyList()
)
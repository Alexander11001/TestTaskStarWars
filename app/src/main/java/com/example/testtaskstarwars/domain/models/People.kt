package com.example.testtaskstarwars.domain.models

//имя, пол и количество звездолетов, которые пилотировал этот человек
data class People(
    val name: String,
    val gender: String,
    //Only quantity of starships required
    val starships: Int,
    val films: List<String>
)

data class DataPeople(
    val results: List<People>
)



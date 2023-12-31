package com.example.testtaskstarwars.data.api

import com.example.testtaskstarwars.data.dto.DataPeopleDTO
import com.example.testtaskstarwars.data.dto.DataPlanetsDTO
import com.example.testtaskstarwars.data.dto.DataStarshipsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(): DataPeopleDTO

    @GET("people/")
    suspend fun getPeopleBySearch(@Query("search") name: String): DataPeopleDTO

    //Search by model and name
    @GET("starships/")
    suspend fun getStarshipsBySearch(@Query("search") name: String): DataStarshipsDTO

    @GET("planets/")
    suspend fun getPlanetsBySearch(@Query("search") name: String): DataPlanetsDTO
}
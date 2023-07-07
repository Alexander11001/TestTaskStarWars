package com.example.testtaskstarwars.data.api

import com.example.testtaskstarwars.data.dto.DataPeopleDTO
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people")
    suspend fun getPeopleBySearch(): DataPeopleDTO
}
package com.example.testtaskstarwars.data.repository

import com.example.testtaskstarwars.data.api.StarWarsApi
import com.example.testtaskstarwars.data.dto.toDataPeople
import com.example.testtaskstarwars.data.dto.toPeopleList
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: StarWarsApi) : NetworkRepository {

    override suspend fun loadPeople(): List<People> {
        val dtoList = api.getPeopleBySearch().toDataPeople()
        return dtoList.toPeopleList()
    }
}
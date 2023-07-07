package com.example.testtaskstarwars.data.repository

import com.example.testtaskstarwars.data.api.StarWarsApi
import com.example.testtaskstarwars.data.dto.toDataPeople
import com.example.testtaskstarwars.data.dto.toPeopleList
import com.example.testtaskstarwars.domain.models.People
import com.example.testtaskstarwars.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: StarWarsApi) : NetworkRepository {

    override fun loadPeople(): Flow<List<People>> = flow {
        val dtoList = api.getPeopleBySearch().toDataPeople()
        emit (dtoList.toPeopleList())
    }
    }

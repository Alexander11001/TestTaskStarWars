package com.example.testtaskstarwars.domain.repository

import com.example.testtaskstarwars.domain.models.People
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

     fun loadPeople(): Flow<List<People>>
}
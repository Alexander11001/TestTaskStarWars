package com.example.testtaskstarwars.domain.repository

import com.example.testtaskstarwars.domain.models.People

interface NetworkRepository {

    suspend fun loadPeople(): List<People>
}
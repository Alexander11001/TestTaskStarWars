package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val director: String,
    val producer: String,
)
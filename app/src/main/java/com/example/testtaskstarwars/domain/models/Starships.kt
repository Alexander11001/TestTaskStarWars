package com.example.testtaskstarwars.domain.models

// «имя, модель, производитель, пассажиры» для звездолетов.
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarshipsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
)

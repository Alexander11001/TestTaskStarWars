package com.example.testtaskstarwars.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtaskstarwars.domain.models.People

@Entity
data class PeopleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val gender: String,
    val starships: Int,
)

@Entity(primaryKeys = ["peopleId", "filmId"])
data class PeopleFilmCrossRef(
    val peopleId: Int,
    val filmId: Int
)

fun People.toEntity(): PeopleEntity {
    return PeopleEntity(
        id = 0,
        name = this.name,
        gender = this.gender,
        starships = this.starships
    )
}

fun PeopleEntity.toPeople(): People {
    return People(
        name = this.name,
        gender = this.gender,
        starships = this.starships,
        films = listOf()
    )
}


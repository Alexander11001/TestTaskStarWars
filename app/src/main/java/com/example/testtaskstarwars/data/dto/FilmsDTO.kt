package com.example.testtaskstarwars.data.dto

import com.google.gson.annotations.SerializedName

data class FilmsDTO (
    @SerializedName("title")
    val title: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("producer")
    val producer: String?
)

data class DataFilmsDTO(
    @SerializedName("results")
    val results: List<FilmsDTO>?
)
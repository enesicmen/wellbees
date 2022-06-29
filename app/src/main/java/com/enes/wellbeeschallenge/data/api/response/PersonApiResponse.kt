package com.enes.wellbeeschallenge.data.api.response
import com.enes.wellbeeschallenge.data.model.PersonModel

data class PersonApiResponse(
    val id: Int = 0,
    val person: List<PersonModel> = listOf()
)

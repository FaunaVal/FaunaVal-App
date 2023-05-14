package com.torregrosa.faunaval.model

data class ApiResponse(
    var animalList: List<Animal> = emptyList()
)
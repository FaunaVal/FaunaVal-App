package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.model.Animal
import com.torregrosa.faunaval.model.ApiResponse
import retrofit2.http.GET

interface RestDataSource {

    @GET("animales")
    suspend fun getAnimalList(): List<Animal>

}
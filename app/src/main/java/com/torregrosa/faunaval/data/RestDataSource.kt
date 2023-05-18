package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.model.Animal
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET("animales")
    suspend fun getAnimalList(): List<Animal>

    @GET("animales/{id}")
    suspend fun getAnimalById(
        @Path("id") id: Int
    ): Animal

}
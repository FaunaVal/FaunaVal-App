package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.model.Animal
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET("animales/list/{id}")
    suspend fun getAnimalList(
        @Path("id") id: Int
    ): List<Animal>

    @GET("animales/list/{id}/{filter}")
    suspend fun getAnimalListFiltered(
        @Path("id") id: Int,
        @Path("filter") filter: String?
    ): List<Animal>

    @GET("animales/{id}")
    suspend fun getAnimalById(
        @Path("id") id: Int
    ): Animal

}
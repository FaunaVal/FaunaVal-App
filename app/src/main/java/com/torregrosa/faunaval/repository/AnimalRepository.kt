package com.torregrosa.faunaval.repository

import com.torregrosa.faunaval.data.RestDataSource
import com.torregrosa.faunaval.model.Animal
import javax.inject.Inject

interface AnimalRepository {

    suspend fun getAnimalList(): List<Animal>
    suspend fun getAnimalById(id: Int): Animal
}

class AnimalRepositoryImp @Inject constructor(
    private val restDataSource: RestDataSource
) : AnimalRepository {

    override suspend fun getAnimalList(): List<Animal> {
        return restDataSource.getAnimalList()
    }

    override suspend fun getAnimalById(id: Int): Animal {
        return restDataSource.getAnimalById(id)
    }

}
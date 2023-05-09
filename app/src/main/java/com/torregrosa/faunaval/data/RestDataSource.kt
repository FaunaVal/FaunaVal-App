package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.model.Animal

interface RestDataSource {

    suspend fun getAnimal(): Animal

}
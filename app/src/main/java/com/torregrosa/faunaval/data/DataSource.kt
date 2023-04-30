package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.model.Animal

class DataSource {
    fun loadAnimals(): List<Animal>{
        return listOf(Animal(name = "Perro"),
            Animal(name = "Gato"),
            Animal(name = "Pájaro"),
            Animal(name = "Cocodrilo"),
            Animal(name = "Lagarto"))
    }
}
package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.model.Animal

object DataSource {
    fun loadAnimals(): List<Animal> {
        return listOf(
            Animal(name = "Perro"),
            Animal(name = "Gato"),
            Animal(name = "Pájaro"),
            Animal(name = "Cocodrilo"),
            Animal(name = "Lagarto")
        )
    }

    val buttonsOptions = listOf(
        Pair(R.string.identificar, 1),
        Pair(R.string.explorar, 6),
        Pair(R.string.colaborar, 12)
    )
}
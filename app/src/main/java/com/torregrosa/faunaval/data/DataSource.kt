package com.torregrosa.faunaval.data

import com.torregrosa.faunaval.R
import com.torregrosa.faunaval.model.Animal

object DataSource {


    val buttonsOptions = listOf(
        Pair(R.string.identificar, 0),
        Pair(R.string.explorar, 1),
        Pair(R.string.colaborar, 2)
    )

    val buttonsCategories = listOf(
        Pair(R.string.mamiferos, 0),
        Pair(R.string.aves, 1),
        Pair(R.string.reptiles, 2),
        Pair(R.string.anfibios, 3)
    )
}
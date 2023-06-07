package com.torregrosa.faunaval.model

data class Animal(
    val id: Int,
    val nombreComun: String,
    val nombreCient: String,
    val clase: String,
    val orden: String,
    val familia: String,
    val descripcion: String,
    val fotos: List<Foto>
)
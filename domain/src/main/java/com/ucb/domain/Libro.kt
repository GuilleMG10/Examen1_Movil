package com.ucb.domain

data class Libro(
    val id: Int = 0,
    val autor: List<String>,
    val titulo: String,
    val AnioPub: String,
    var favorito: Boolean = false
)
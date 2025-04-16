package com.ucb.usecase

import com.ucb.domain.Libro

class LibroBuscado {
    fun invoke(toSearch: String): List<Libro> {
        return listOf(
            Libro(listOf("prueba"), "pepe1", "2019"),

        )
    }
}
package com.ucb.data.libro


import com.ucb.domain.Libro

interface ILibroLocalDataSource {
    suspend fun guardarLibro(libro: Libro): Boolean
    suspend fun obtenerFavoritos(): List<Libro>
}
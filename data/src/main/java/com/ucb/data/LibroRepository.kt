package com.ucb.data


import com.ucb.data.libro.IlibroRemoteDataSource
import com.ucb.data.libro.ILibroLocalDataSource
import com.ucb.domain.Libro

class LibroRepository (
    val bookRemoteDataSource: IlibroRemoteDataSource,
    val libroLocalDataSource: ILibroLocalDataSource
) {
    suspend fun searchByQuery(query: String): List<Libro> {
        return bookRemoteDataSource.searchByQuery(query)
    }
    suspend fun guardarLibro(libro: Libro): Boolean {
        libroLocalDataSource.guardarLibro(libro)
        return true
    }
}
package com.ucb.data


import com.ucb.data.libro.IlibroRemoteDataSource
import com.ucb.domain.Libro

class LibroRepository (
    val bookRemoteDataSource: IlibroRemoteDataSource
) {
    suspend fun searchByQuery(query: String): List<Libro> {
        return bookRemoteDataSource.searchByQuery(query)
    }
}
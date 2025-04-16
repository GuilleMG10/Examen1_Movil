package com.ucb.data.libro

import com.ucb.domain.Libro

interface IlibroRemoteDataSource {
    suspend fun searchByQuery(query: String) : List<Libro>
}
package com.ucb.framework.libro

import com.ucb.data.libro.IlibroRemoteDataSource
import com.ucb.domain.Libro
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitClient

class BookRemoteDataSource (
    val retrofitServise: RetrofitClient
) : IlibroRemoteDataSource {
    override suspend fun searchByQuery(query: String) : List<Libro> {
        return retrofitServise.apiService.searchBooks(query).toModel()
    }
}
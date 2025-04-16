package com.ucb.framework.service


import com.ucb.framework.dto.LibroResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): LibroResponseDto
}
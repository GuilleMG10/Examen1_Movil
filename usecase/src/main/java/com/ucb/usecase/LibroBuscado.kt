package com.ucb.usecase

import com.ucb.domain.Libro
import com.ucb.data.LibroRepository
import kotlinx.coroutines.delay

class LibroBuscado (
    val bookRepository: LibroRepository
) {
    suspend fun invoke(toSearch: String): List<Libro> {
        delay(100)
        return bookRepository.searchByQuery(toSearch)
    }
}
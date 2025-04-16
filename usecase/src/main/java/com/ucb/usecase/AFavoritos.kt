package com.ucb.usecase

import com.ucb.data.LibroRepository
import com.ucb.domain.Libro

class AFavoritos (
    val libroRepository: LibroRepository
    )
    {
        suspend fun invoke(libro: Libro) {
            libroRepository.guardarLibro(libro)
        }
    }

package com.ucb.framework.mappers

import com.ucb.domain.Libro
import com.ucb.framework.dto.LibroDto
import com.ucb.framework.dto.LibroResponseDto

fun LibroDto.toDomain(): Libro {
    return Libro(
        autor = authorName ?: listOf("Autor desconocido"),
        titulo = title ?: "Título desconocido",
        AnioPub = firstPublishYear?.toString() ?: "Año desconocido"
    )
}

fun LibroResponseDto.toModel(): List<Libro> {
    return this.docs.map { it.toDomain() }
}
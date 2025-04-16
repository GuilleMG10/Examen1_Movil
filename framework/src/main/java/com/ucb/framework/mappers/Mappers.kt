package com.ucb.framework.mappers

import com.ucb.domain.Libro
import com.ucb.framework.dto.LibroDto
import com.ucb.framework.dto.LibroResponseDto
import com.ucb.framework.persistence.LibroEntity

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

fun Libro.toEntity(): LibroEntity {
    return LibroEntity(
        title = this.titulo,
        authors = this.autor.joinToString(),
        publicationYear = this.AnioPub
    )
}

fun LibroEntity.toDomain(): Libro {
    return Libro(
        titulo = this.title,
        autor = this.authors.split(",").map { it.trim() },
        AnioPub = this.publicationYear
    )
}

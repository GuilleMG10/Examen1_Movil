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
        id = this.id,
        title = this.titulo,
        authors = this.autor.joinToString(", "),
        publicationYear = this.AnioPub,
        favorito = this.favorito
    )
}

fun LibroEntity.toDomain(): Libro {
    return Libro(
        id = this.id,
        titulo = this.title,
        AnioPub = this.publicationYear,
        autor = this.authors.split(", "), // Suponemos que los autores están separados por comas
        favorito = this.favorito // Mapeamos la propiedad 'favorito'
    )
}
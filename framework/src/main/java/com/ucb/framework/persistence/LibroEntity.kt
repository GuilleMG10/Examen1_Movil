package com.ucb.framework.persistence


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class LibroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val authors: String,
    val publicationYear: String,
    val favorito: Boolean = false
)
package com.ucb.framework.libro

import android.content.Context
import com.ucb.data.libro.ILibroLocalDataSource
import com.ucb.domain.Libro
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.persistence.AppRoomDatabase

class LibroLocalDataSource (val context: Context) : ILibroLocalDataSource {
    val libroDao = AppRoomDatabase.getDatabase(context).libroDao()
    override suspend fun guardarLibro(libro: Libro): Boolean {
        libroDao.guardarLibro(libro.toEntity())
        return true
    }
}
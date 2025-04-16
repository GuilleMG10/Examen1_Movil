package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ucb.domain.Libro

@Dao
interface ILibroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarLibro(libro: LibroEntity)
}
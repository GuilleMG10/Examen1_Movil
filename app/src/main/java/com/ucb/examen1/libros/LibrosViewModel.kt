package com.ucb.examen1.libros

import androidx.lifecycle.ViewModel
import com.ucb.domain.Libro
import com.ucb.usecase.LibroBuscado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LibrosViewModel : ViewModel(){
    val libroBuscado = LibroBuscado()
    private val _buscado = MutableStateFlow<List<Libro>>(emptyList())
    val buscado: StateFlow<List<Libro>> = _buscado
    fun buscarLibros(buscar: String) {
        _buscado.value = libroBuscado.invoke(buscar)
    }
}

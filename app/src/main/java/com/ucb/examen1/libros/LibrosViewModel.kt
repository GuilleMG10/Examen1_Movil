package com.ucb.examen1.libros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Libro
import com.ucb.usecase.LibroBuscado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibrosViewModel @Inject constructor(
    private val searchBooks: LibroBuscado
) : ViewModel() {

    sealed class LibroState {
        object Init : LibroState()
        object Loading : LibroState()
        class Successful(val libros: List<Libro>) : LibroState()
    }

    private val _flow = MutableStateFlow<LibroState>(LibroState.Init)
    val flow: StateFlow<LibroState> = _flow

    fun search(query: String) {
        viewModelScope.launch {
            _flow.value = LibroState.Loading
            val result = searchBooks.invoke(query)
            _flow.value = LibroState.Successful(result)
        }
    }
}

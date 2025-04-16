import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Libro
import com.ucb.examen1.libros.LibrosViewModel
@Composable
fun BuscarLibroUI2() {
    val viewModel: LibrosViewModel = hiltViewModel()
    var query by remember { mutableStateOf("") }
    var mostrarFavoritos by remember { mutableStateOf(false) }
    val librosfavs = remember { mutableStateListOf<Libro>() }
    val state by viewModel.flow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "🔍 Buscar Libro",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Nombre del libro") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Fila con los botones de "Buscar" y "Ver Favoritos"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.search(query) // Acción para realizar la búsqueda
                    mostrarFavoritos = false // Cambiar a búsqueda
                },
                enabled = query.isNotBlank(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Buscar")
            }

            // Botón para ver favoritos
            Button(
                onClick = {
                    viewModel.verFavoritos() // Acción para mostrar los favoritos
                    mostrarFavoritos = true // Cambiar a favoritos
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Ver Favoritos")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (val current = state) {
            is LibrosViewModel.LibroState.Init -> {
                Text(
                    text = "Ingresa un término de búsqueda para comenzar.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            is LibrosViewModel.LibroState.Loading -> {
                CircularProgressIndicator()
            }

            is LibrosViewModel.LibroState.Successful -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    val librosAMostrar = if (mostrarFavoritos) {
                        current.libros.filter { it.favorito }
                    } else {
                        current.libros
                    }

                    items(current.libros) { libro ->
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "📚 ${libro.titulo}",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = "Año de publicación: ${libro.AnioPub}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "Autor(es): ${libro.autor.joinToString()}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                val isFavorite = librosfavs.contains(libro)

                                IconButton(onClick = {
                                    if (isFavorite) {
                                        librosfavs.remove(libro)
                                    } else {
                                        librosfavs.add(libro)
                                        viewModel.anadirAFavoritos(libro)
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = if (isFavorite) "Remover de favoritos" else "Agregar a favoritos"
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

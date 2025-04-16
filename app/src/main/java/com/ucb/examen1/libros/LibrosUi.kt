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
    val state by viewModel.flow.collectAsState()
    val librosFav = remember { mutableStateListOf<Libro>() }

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Acción para volver */ },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("← Volver")
            }

            Button(
                onClick = { viewModel.search(query) },
                enabled = query.isNotBlank(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Buscar")
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
                    items(current.libros) { libro ->
                        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
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

                            IconButton(
                                onClick = {
                                    if (librosFav.contains(libro)) {
                                        librosFav.remove(libro)
                                    } else {
                                        librosFav.add(libro)
                                        viewModel.anadirAFavoritos(libro)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (librosFav.contains(libro)) Icons.Default.Star else Icons.Default.StarOutline,
                                    contentDescription = if (librosFav.contains(libro)) "Quitar de destacados" else "Marcar como destacado"
                                )


                            }
                        }
                    }
                }
            }
        }
    }
}

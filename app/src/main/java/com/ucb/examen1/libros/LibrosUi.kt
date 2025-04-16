package com.ucb.examen1.libros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BuscarLibroUI() {

    val viewModel: LibrosViewModel = viewModel()
    var query by remember { mutableStateOf("") }
    val results by viewModel.buscado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = " Buscar Libros",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Nombre del libro") },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {  },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Atrás")
            }

            Button(
                onClick = { viewModel.buscarLibros(query) },
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Buscar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (results.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(0.95f)
            ) {
                items(results) { libro ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = libro.titulo,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Año: ${libro.AnioPub}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "Autores: ${libro.autor.joinToString()}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            Text(
                text = "No hay resultados todavía",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

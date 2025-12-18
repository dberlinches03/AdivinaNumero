package com.example.examenPMM.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenPMM.viewmodel.AdivinaNumeroViewModel

// La ventana principal del juego
@Composable
fun AdivinaScreen(viewModel: AdivinaNumeroViewModel = viewModel(), modifier: Modifier = Modifier) {
    val uiState = viewModel.uiState.value
    var valorEntrada by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Adivina el número", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = valorEntrada,
            onValueChange = { valorEntrada = it },
            label = { Text("Tu intento") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = { viewModel.comprobarIntento(valorEntrada) }) {
                Text("Comprobar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.reiniciarJuego(); valorEntrada = "" }) {
                Text("Reiniciar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(uiState.mensaje)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Historial de intentos:")
        LazyColumn {
            item {
                Text("Numero de intentos: " + uiState.intentos.size)
            }
            items(uiState.intentos) { intento ->
                Text(intento)
            }
        }
    }
}

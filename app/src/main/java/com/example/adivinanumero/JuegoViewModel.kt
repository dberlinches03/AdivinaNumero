package com.example.adivinanumero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.adivinanumero.data.JuegoUiState
import kotlin.random.Random

class JuegoViewModel : ViewModel() {

    private val _uiState = mutableStateOf(JuegoUiState(numeroSecreto = Random.nextInt(1, 101)))
    val uiState: State<JuegoUiState> = _uiState

    fun comprobarIntento(valor: String) {
        val numero = valor.toIntOrNull()
        if (numero == null) {
            _uiState.value = _uiState.value.copy(mensaje = "Entrada inválida")
            return
        }

        val nuevoHistorial = _uiState.value.intentos.toMutableList()
        val nuevoMensaje = when {
            numero < _uiState.value.numeroSecreto -> {
                nuevoHistorial.add("$numero → bajo")
                "Demasiado bajo"
            }
            numero > _uiState.value.numeroSecreto -> {
                nuevoHistorial.add("$numero → alto")
                "Demasiado alto"
            }
            else -> {
                nuevoHistorial.add("$numero → acertado")
                "¡Correcto!"
            }
        }

        _uiState.value = _uiState.value.copy(
            mensaje = nuevoMensaje,
            intentos = nuevoHistorial
        )
    }

    fun reiniciarJuego() {
        _uiState.value = JuegoUiState(numeroSecreto = Random.nextInt(1, 101))
    }
}

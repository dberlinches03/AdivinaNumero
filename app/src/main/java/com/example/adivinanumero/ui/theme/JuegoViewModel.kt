package com.example.adivinanumero.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.adivinanumero.data.Juego
import kotlin.random.Random

class JuegoViewModel : ViewModel() {

    private val _uiState =
        mutableStateOf(Juego(numeroSecreto = Random.Default.nextInt(1, 101)))
    val uiState: State<Juego> = _uiState

    fun comprobarIntento(valor: String) {
        val numero = valor.toIntOrNull()
        if (numero == null) {
            _uiState.value = _uiState.value.copy(mensaje = "Entrada inválida")
            return
        }

        // Guardo en un historial la pistas y informo si se acerca al numero o lo ha acertado
        val nuevoHistorial = _uiState.value.intentos.toMutableList()
        // Creo un nuevo mensaje cuando el numero introducido sea menor que el numero secreto o mayor o sino se ha acertado
        val nuevoMensaje = when {
            numero < _uiState.value.numeroSecreto -> {
                _uiState.value.mensaje = "El numero es mayor"
                nuevoHistorial.add("$numero → ${_uiState.value.mensaje}")
                "Es mayor que $numero"
            }
            numero > _uiState.value.numeroSecreto -> {
                _uiState.value.mensaje = "El numero es menor"
                nuevoHistorial.add("$numero → ${_uiState.value.mensaje}")
                "Es menor que $numero"
            }
            else -> {
                _uiState.value.mensaje = "Numero Correcto"
                nuevoHistorial.add("$numero → ${_uiState.value.mensaje}")
                "Has acertado era $numero"
            }
        }

        // Hago una copia del mensaje o pista anterior
        _uiState.value = _uiState.value.copy(
            mensaje = nuevoMensaje,
            intentos = nuevoHistorial
        )
    }

    // Funcion para reiniciar el juego
    fun reiniciarJuego() {
        _uiState.value = Juego(numeroSecreto = Random.Default.nextInt(1, 101))
    }
}
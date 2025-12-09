package com.example.adivinanumero.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.adivinanumero.data.Juego
import kotlin.random.Random

class AdivinaNumeroViewModel : ViewModel() {

    private val _uiState =
        mutableStateOf(Juego(numeroSecreto = Random.Default.nextInt(1, 101)))
    val uiState: State<Juego> = _uiState

    // Comprobamos que el intento sea valido
    fun comprobarIntento(valor: String) {
        val numero = valor.toIntOrNull() // numero a introducir
        if (numero == null) {
            _uiState.value = _uiState.value.copy(mensaje = "Entrada inválida")
            return
        }

        val nuevoHistorial = _uiState.value.intentos.toMutableList()
        val nuevoMensaje = when {
            numero < _uiState.value.numeroSecreto -> {
                _uiState.value.mensaje = "El numero es mayor" // pista
                nuevoHistorial.add("$numero -> ${_uiState.value.mensaje}")
                "Es mayor que $numero"
            }
            numero > _uiState.value.numeroSecreto -> {
                _uiState.value.mensaje = "El numero es menor" //pista
                nuevoHistorial.add("$numero -> ${_uiState.value.mensaje}")
                "Es menor que $numero"
            }
            else -> {
                _uiState.value.mensaje = "Número Correcto" // has acertado
                nuevoHistorial.add("$numero -> ${_uiState.value.mensaje}")
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
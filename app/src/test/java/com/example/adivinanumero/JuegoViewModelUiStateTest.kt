package com.example.adivinanumero

import org.junit.Assert.*
import org.junit.Test

class JuegoViewModelUiStateTest {

    // CASOS DE ÉXITO (happy path)
    @Test
    fun intentoCorrectoActualizaUiState() {
        val viewModel = JuegoViewModel()
        val secreto = viewModel.uiState.value.numeroSecreto

        viewModel.comprobarIntento(secreto.toString())

        val estado = viewModel.uiState.value
        assertEquals("¡Correcto!", estado.mensaje)
        assertTrue(estado.intentos.last().contains("acertado"))
    }

    @Test
    fun intentoDemasiadoBajoActualizaUiState() {
        val viewModel = JuegoViewModel()
        val secreto = viewModel.uiState.value.numeroSecreto

        viewModel.comprobarIntento((secreto - 1).toString())

        val estado = viewModel.uiState.value
        assertEquals("Demasiado bajo", estado.mensaje)
        assertTrue(estado.intentos.last().contains("bajo"))
    }

    @Test
    fun intentoDemasiadoAltoActualizaUiState() {
        val viewModel = JuegoViewModel()
        val secreto = viewModel.uiState.value.numeroSecreto

        viewModel.comprobarIntento((secreto + 1).toString())

        val estado = viewModel.uiState.value
        assertEquals("Demasiado alto", estado.mensaje)
        assertTrue(estado.intentos.last().contains("alto"))
    }

    // CASOS DE ERROR (error path)
    @Test
    fun entradaInvalidaActualizaUiState() {
        val viewModel = JuegoViewModel()
        viewModel.comprobarIntento("abc")

        val estado = viewModel.uiState.value
        assertEquals("Entrada inválida", estado.mensaje)
        assertTrue(estado.intentos.isEmpty()) // no debe añadir nada al historial
    }

    // CASOS LÍMITE (boundary case)
    @Test
    fun reiniciarJuegoGeneraNuevoNumeroYReseteaHistorial() {
        val viewModel = JuegoViewModel()
        val numeroInicial = viewModel.uiState.value.numeroSecreto

        viewModel.comprobarIntento(numeroInicial.toString()) // acertamos
        viewModel.reiniciarJuego()

        val estado = viewModel.uiState.value
        assertNotEquals(numeroInicial, estado.numeroSecreto) // nuevo número secreto
        assertEquals("Introduce un número del 1 al 100", estado.mensaje)
        assertTrue(estado.intentos.isEmpty())
    }
}

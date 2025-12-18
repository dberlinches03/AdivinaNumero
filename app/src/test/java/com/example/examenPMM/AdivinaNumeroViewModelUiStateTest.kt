package com.example.examenPMM

import com.example.examenPMM.viewmodel.AdivinaNumeroViewModel
import org.junit.Assert.*
import org.junit.Test

class AdivinaNumeroViewModelUiStateTest {

    // CASOS DE ÉXITO (happy path)
    @Test
    fun intentoCorrectoActualizaUiState() {
        val viewModel = AdivinaNumeroViewModel()
        val secreto = viewModel.uiState.value.numeroSecreto
        viewModel.comprobarIntento(secreto.toString())

        val estado = viewModel.uiState.value
        assertEquals("Has acertado era $secreto", estado.mensaje)
        assertTrue(estado.intentos.last().contains("Correcto"))
    }

    // CASOS DE ERROR (error path)
    @Test
    fun entradaInvalidaActualizaUiState() {
        val viewModel = AdivinaNumeroViewModel()
        viewModel.comprobarIntento("abc")

        val estado = viewModel.uiState.value
        assertEquals("Entrada inválida", estado.mensaje)
        assertTrue(estado.intentos.isEmpty()) // no debe añadir nada al historial
    }

    // CASOS LÍMITE (boundary case)
    @Test
    fun reiniciarJuegoGeneraNuevoNumeroYReseteaHistorial() {
        val viewModel = AdivinaNumeroViewModel()
        val numeroInicial = viewModel.uiState.value.numeroSecreto

        viewModel.comprobarIntento(numeroInicial.toString()) // acertamos
        viewModel.reiniciarJuego()

        val estado = viewModel.uiState.value
        assertNotEquals(numeroInicial, estado.numeroSecreto) // nuevo número secreto
        assertEquals("Introduce un número del 1 al 100", estado.mensaje)
        assertTrue(estado.intentos.isEmpty())
    }
}

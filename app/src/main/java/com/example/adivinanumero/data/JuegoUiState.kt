package com.example.adivinanumero.data

data class JuegoUiState(
    val numeroSecreto: Int = 0,
    val mensaje: String = "Introduce un número del 1 al 100",
    val intentos: List<String> = emptyList()
)
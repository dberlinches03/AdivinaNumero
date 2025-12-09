package com.example.adivinanumero.data

// Datos del juego
data class Juego(
    val numeroSecreto: Int = 0,
    var mensaje: String = "Introduce un número del 1 al 100",
    val intentos: List<String> = emptyList() // historial de intentos
)
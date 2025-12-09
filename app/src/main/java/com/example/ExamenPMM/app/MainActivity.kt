package com.example.ExamenPMM.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ExamenPMM.ui.theme.AdivinaNumeroTheme
import com.example.ExamenPMM.ui.theme.AdivinaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdivinaNumeroTheme {
                Scaffold(modifier = Modifier.Companion) { innerPadding ->
                    AdivinaScreen(modifier = Modifier.Companion.padding(innerPadding))
                }
            }
        }
    }
}
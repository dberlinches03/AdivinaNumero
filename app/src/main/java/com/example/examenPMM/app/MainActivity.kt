package com.example.examenPMM.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.examenPMM.ui.theme.AdivinaNumeroTheme
import com.example.examenPMM.ui.theme.AdivinaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdivinaNumeroTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    AdivinaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
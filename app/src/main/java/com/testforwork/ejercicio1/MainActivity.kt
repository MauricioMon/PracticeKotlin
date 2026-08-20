package com.testforwork.ejercicio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.testforwork.ejercicio1.core.navigation.AppNavGraph
import com.testforwork.ejercicio1.core.ui.theme.Ejercicio1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1Theme {
                AppNavGraph()
            }
        }
    }
}
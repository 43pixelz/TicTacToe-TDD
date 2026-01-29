package com.example.tictactoe_tdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tictactoe_tdd.presentation.ui.screens.GameScreen
import com.example.tictactoe_tdd.presentation.ui.theme.TicTacToeTDDTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTDDTheme {
                GameScreen()
            }
        }
    }
}
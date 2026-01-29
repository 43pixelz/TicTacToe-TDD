package com.example.tictactoe_tdd.presentation.effects

sealed class GameEffect {
    data class ShowSnackbar(val message: String) : GameEffect()
}
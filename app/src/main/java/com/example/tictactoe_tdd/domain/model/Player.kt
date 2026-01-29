package com.example.tictactoe.domain.model

enum class Player {
    X, O;
    fun opponent() = if (this == X) O else X
}

package com.example.tictactoe.domain.model

sealed class GameResult {
    object InProgress : GameResult()
    data class Winner(val player: Player) : GameResult()
}

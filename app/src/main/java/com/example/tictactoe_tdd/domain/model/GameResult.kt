package com.example.tictactoe.domain.model

sealed class GameResult {
    object InProgress : GameResult()
    object Draw : GameResult()
    data class Winner(val player: Player) : GameResult()
}

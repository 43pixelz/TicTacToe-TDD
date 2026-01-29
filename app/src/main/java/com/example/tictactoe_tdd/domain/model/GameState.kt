package com.example.tictactoe_tdd.domain.model

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player


data class GameState(
    val board: List<Cell>,
    val currentPlayer: Player,
    val result: GameResult
) {
    companion object {
        fun newGame() = GameState(
            board = List(9) { Cell(it) },
            currentPlayer = Player.X,
            result = GameResult.InProgress
        )
    }
}

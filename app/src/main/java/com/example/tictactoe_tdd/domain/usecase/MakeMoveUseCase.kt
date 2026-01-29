package com.example.tictactoe_tdd.domain.usecase

import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe_tdd.domain.repository.GameRepository
import com.example.tictactoe_tdd.domain.rules.GameRules
import jakarta.inject.Inject


class MakeMoveUseCase @Inject constructor(
    private val repository: GameRepository,
    private val rules: GameRules
) {
    operator fun invoke(index: Int) {
        val state = repository.gameState.value
        if (state.board[index].player != null) return

        val updatedBoard = state.board.toMutableList().apply {
            this[index] = this[index].copy(player = state.currentPlayer)
        }
        val result = rules.validate(updatedBoard)
        repository.updateState(
            state.copy(
                board = updatedBoard,
                currentPlayer = if (result is GameResult.InProgress) state.currentPlayer.opponent() else state.currentPlayer,
                result = result
            )
        )
    }
}
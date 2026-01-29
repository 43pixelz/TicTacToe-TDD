package com.example.tictactoe_tdd.domain.usecase

import com.example.tictactoe_tdd.domain.repository.GameRepository
import jakarta.inject.Inject


class MakeMoveUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(index: Int) {
        val state = repository.gameState.value
        if (state.board[index].player != null) return

        val updatedBoard = state.board.toMutableList().apply {
            this[index] = this[index].copy(player = state.currentPlayer)
        }
        repository.updateState(
            state.copy(
                board = updatedBoard,
                currentPlayer = state.currentPlayer.opponent()
            )
        )
    }
}
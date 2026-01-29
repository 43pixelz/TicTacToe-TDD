package com.example.tictactoe_tdd.domain.repository

import com.example.tictactoe_tdd.domain.model.GameState
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {
    val gameState: StateFlow<GameState>
    fun updateState(state: GameState)
    fun reset()
}
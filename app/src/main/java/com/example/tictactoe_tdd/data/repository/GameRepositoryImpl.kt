package com.example.tictactoe_tdd.data.repository

import com.example.tictactoe_tdd.domain.model.GameState
import com.example.tictactoe_tdd.domain.repository.GameRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameRepositoryImpl @Inject constructor() : GameRepository {
    private val _gameState = MutableStateFlow(GameState.newGame())
    override val gameState = _gameState.asStateFlow()

    override fun updateState(state: GameState) {
        _gameState.value = state
    }

    override fun reset() {
        _gameState.value = GameState.newGame()
    }

}
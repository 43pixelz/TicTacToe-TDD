package com.example.tictactoe_tdd

import com.example.tictactoe_tdd.domain.model.GameState
import com.example.tictactoe_tdd.domain.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class FakeGameRepository : GameRepository {

    private val _state = MutableStateFlow(GameState.newGame())
    override val gameState: StateFlow<GameState> = _state

    override fun updateState(state: GameState) {
        _state.value = state
    }

    override fun reset() {
        _state.value = GameState.newGame()
    }
}

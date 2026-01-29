package com.example.tictactoe_tdd.data.repository

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe_tdd.domain.model.GameState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class GameRepositoryImplTest {
    @Test
    fun `initial state is emitted`() = runTest {
        val repository = GameRepositoryImpl()

        val state = repository.gameState.first()

        assertEquals(GameState.newGame(), state)
    }

    @Test
    fun `updateState emits new state`() = runTest {
        val repository = GameRepositoryImpl()

        val newState = GameState(
            currentPlayer = Player.O,
            board = List(9) { Cell(it) },
            result = GameResult.InProgress
        )

        repository.updateState(newState)

        val state = repository.gameState.first()

        assertEquals(newState, state)
    }

}
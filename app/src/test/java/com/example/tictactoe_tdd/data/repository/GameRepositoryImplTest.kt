package com.example.tictactoe_tdd.data.repository

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
}
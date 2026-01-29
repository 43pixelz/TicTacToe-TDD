package com.example.tictactoe_tdd.domain.usecase

import com.example.tictactoe.domain.model.Player
import com.example.tictactoe_tdd.FakeGameRepository
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MakeMoveUseCaseTest {

    @Test
    fun `placing move updates board`() {
        val repo = FakeGameRepository()
        val useCase = MakeMoveUseCase(repo)

        useCase(0)

        val state = repo.gameState.value
        assertEquals(Player.X, state.board[0].player)
    }

    @Test
    fun `player switches after move`() {
        val repo = FakeGameRepository()
        val useCase = MakeMoveUseCase(repo)

        useCase(0)

        val state = repo.gameState.value

        assertEquals(Player.O, state.currentPlayer)
    }


}
package com.example.tictactoe_tdd.domain.usecase

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe_tdd.FakeGameRepository
import com.example.tictactoe_tdd.domain.model.GameState
import com.example.tictactoe_tdd.domain.rules.GameRules
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MakeMoveUseCaseTest {

    @Test
    fun `placing move updates board`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        useCase(0)

        val state = repo.gameState.value
        assertEquals(Player.X, state.board[0].player)
    }

    @Test
    fun `player switches after move`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        useCase(0)

        val state = repo.gameState.value

        assertEquals(Player.O, state.currentPlayer)
    }

    @Test
    fun `move on occupied cell is ignored`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        useCase(0)
        useCase(0)

        val state = repo.gameState.value
        assertEquals(Player.X, state.board[0].player)
        assertEquals(Player.O, state.currentPlayer)
    }

    @Test
    fun `winning move updates result`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        repo.updateState(
            GameState(
                board = listOf(
                    Cell(0, Player.X), Cell(1, Player.X), Cell(2),
                    Cell(3), Cell(4), Cell(5),
                    Cell(6), Cell(7), Cell(8)
                ),
                currentPlayer = Player.X,
                result = GameResult.InProgress
            )
        )

        useCase(2)

        val result = repo.gameState.value.result
        assertEquals(GameResult.Winner(Player.X), result)
    }


    @Test
    fun `player does not switch after winning move`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        repo.updateState(
            GameState(
                board = listOf(
                    Cell(0, Player.X), Cell(1, Player.X), Cell(2),
                    Cell(3), Cell(4), Cell(5),
                    Cell(6), Cell(7), Cell(8)
                ),
                currentPlayer = Player.X,
                result = GameResult.InProgress
            )
        )

        useCase(2)

        assertEquals(Player.X, repo.gameState.value.currentPlayer)
    }

    @Test
    fun `moves are ignored after game over`() {
        val repo = FakeGameRepository()
        val rules = GameRules()
        val useCase = MakeMoveUseCase(repo, rules)

        repo.updateState(
            GameState(
                board = listOf(
                    Cell(0), Cell(1, Player.X), Cell(2),
                    Cell(3), Cell(4, Player.X), Cell(5),
                    Cell(6), Cell(7, Player.X), Cell(8)
                ),
                currentPlayer = Player.X,
                result = GameResult.Winner(Player.X)
            )
        )

        useCase(0)

        assertEquals(null, repo.gameState.value.board[0].player)
    }


}
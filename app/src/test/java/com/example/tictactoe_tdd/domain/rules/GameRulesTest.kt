package com.example.tictactoe_tdd.domain.rules

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GameRulesTest {

    private lateinit var rules: GameRules

    @Before
    fun setup() {
        rules = GameRules()
    }

    @Test
    fun `row win should return winner X`() {
        val board = listOf(
            Cell(0, Player.X), Cell(1, Player.X), Cell(2, Player.X),
            Cell(3, null), Cell(4, null), Cell(5, null),
            Cell(6, null), Cell(7, null), Cell(8, null)
        )

        val result = rules.validate(board)

        assertEquals(GameResult.Winner(Player.X), result)
    }

}
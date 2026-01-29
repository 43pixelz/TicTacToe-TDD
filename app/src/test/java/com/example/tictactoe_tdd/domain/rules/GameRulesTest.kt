package com.example.tictactoe_tdd.domain.rules

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import junit.framework.TestCase
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

    @Test
    fun `column win should return winner O`() {
        val board = listOf(
            Cell(0, Player.O), Cell(1, null), Cell(2, null),
            Cell(3, Player.O), Cell(4, null), Cell(5, null),
            Cell(6, Player.O), Cell(7, null), Cell(8, null)
        )

        val result = rules.validate(board)

        TestCase.assertEquals(GameResult.Winner(Player.O), result)
    }

}
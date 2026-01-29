package com.example.tictactoe_tdd.domain.rules

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player

class GameRules {
    fun validate(board: List<Cell>): GameResult {
        val winningCombinations = listOf(
            listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
            listOf(0,3,6), listOf(1,4,7), listOf(2,5,8),
        )
        winningCombinations.forEach { line ->
            val players = line.map { board[it].player }
            if (players.all { it == Player.X }) return GameResult.Winner(Player.X)
            if (players.all { it == Player.O }) return GameResult.Winner(Player.O)
        }
        return GameResult.InProgress
    }
}
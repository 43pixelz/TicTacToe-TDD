package com.example.tictactoe.domain.model

data class Cell(
    val index: Int,
    val player: Player? = null
)

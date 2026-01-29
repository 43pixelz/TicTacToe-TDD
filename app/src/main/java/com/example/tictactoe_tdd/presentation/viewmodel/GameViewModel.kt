package com.example.tictactoe_tdd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe_tdd.domain.repository.GameRepository
import com.example.tictactoe_tdd.domain.usecase.MakeMoveUseCase
import jakarta.inject.Inject

class GameViewModel @Inject constructor(
    private val repository: GameRepository,
    private val makeMoveUseCase: MakeMoveUseCase
) : ViewModel() {
    fun onCellClick(index: Int) {
        makeMoveUseCase(index)
    }

}
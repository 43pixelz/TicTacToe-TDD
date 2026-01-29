package com.example.tictactoe_tdd.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe_tdd.domain.model.GameState
import com.example.tictactoe_tdd.domain.repository.GameRepository
import com.example.tictactoe_tdd.domain.usecase.MakeMoveUseCase
import com.example.tictactoe_tdd.presentation.effects.GameEffect
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GameViewModel @Inject constructor(
    private val repository: GameRepository,
    private val makeMoveUseCase: MakeMoveUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameState.newGame())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<GameEffect>(
        extraBufferCapacity = 1
    )
    val effects = _effects.asSharedFlow()

    private var lastResult: GameResult = GameResult.InProgress

    init {
        viewModelScope.launch {
            repository.gameState.collectLatest { state ->
                _uiState.value = state

                if (state.result != lastResult) {
                    when (state.result) {
                        is GameResult.Winner -> {
                            _effects.tryEmit(
                                GameEffect.ShowSnackbar(
                                    "Winner: ${state.result.player}"
                                )
                            )
                        }
                        GameResult.Draw -> Unit
                        GameResult.InProgress -> Unit
                    }
                }
                lastResult = state.result
            }
        }
    }

    fun onCellClick(index: Int) {
        makeMoveUseCase(index)
    }

}
package com.example.tictactoe_tdd.presentation.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.example.tictactoe_tdd.presentation.effects.GameEffect
import com.example.tictactoe_tdd.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen() {
    val viewModel = hiltViewModel<GameViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val lifeCycleOwner = rememberLifecycleOwner()

    LaunchedEffect(Unit) {
        viewModel.effects
            .flowWithLifecycle(lifeCycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { effect ->
                when (effect) {
                    is GameEffect.ShowSnackbar ->
                        snackbarHostState.showSnackbar(effect.message)
                }
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Text("Game Screen")
            Spacer(Modifier.height(16.dp))
            Text("Current Player: ${state.value.currentPlayer}")
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.value.board) { cell ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .border(2.dp, Color.Black)
                            .clickable {
                                viewModel.onCellClick(cell.index)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(cell.player?.name ?: "")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = viewModel::reset) {
                Text("Reset")
            }
        }
    }

}
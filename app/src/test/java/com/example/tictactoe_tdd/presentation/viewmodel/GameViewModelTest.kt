package com.example.tictactoe_tdd.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe_tdd.FakeGameRepository
import com.example.tictactoe_tdd.domain.model.GameState
import com.example.tictactoe_tdd.domain.usecase.MakeMoveUseCase
import com.example.tictactoe_tdd.presentation.effects.GameEffect
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repository: FakeGameRepository
    private lateinit var makeMoveUseCase: MakeMoveUseCase
    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        repository = FakeGameRepository()

        makeMoveUseCase = mockk(relaxed = true)

        viewModel = GameViewModel(
            repository, makeMoveUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onCellClick delegates to MakeMoveUseCase`() = runTest {
        viewModel.onCellClick(5)

        verify(exactly = 1) {
            makeMoveUseCase.invoke(5)
        }
    }

    @Test
    fun `initial uiState is emitted from repository`() = runTest {
        viewModel.uiState.test {
            assertEquals(GameState.newGame(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `winner emits snackbar effect`() = runTest {
        viewModel.effects.test {

            repository.updateState(
                GameState.newGame().copy(result = GameResult.Winner(Player.X))
            )

            assertEquals(
                GameEffect.ShowSnackbar("Winner: X"),
                awaitItem()
            )
        }
    }


    @Test
    fun `game draw emits snackbar effect`() = runTest {
        viewModel.effects.test {

            repository.updateState(
                GameState.newGame().copy(result = GameResult.Draw)
            )

            assertEquals(
                GameEffect.ShowSnackbar("Game Draw"),
                awaitItem()
            )
        }
    }

    @Test
    fun `onReset delegates to MakeMoveUseCase`() = runTest {
        viewModel.onCellClick(5)
        viewModel.reset()

        verify(exactly = 1) {
            makeMoveUseCase.invoke(5)
        }
        assertEquals(GameState.newGame(), viewModel.uiState.value)
    }

}

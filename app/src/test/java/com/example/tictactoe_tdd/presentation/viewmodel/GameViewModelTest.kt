package com.example.tictactoe_tdd.presentation.viewmodel

import com.example.tictactoe_tdd.FakeGameRepository
import com.example.tictactoe_tdd.domain.usecase.MakeMoveUseCase
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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

}

package com.example.movies.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.application.useCase.GetMoviesUseCase
import com.example.application.useCase.SaveMoviesUseCase
import com.example.domain.aggregate.MovieResponse
import com.example.utilities.Constants.ERROR_GENERAL_MESSAGE
import com.github.testcoroutinesrule.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PageViewModelTest {

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @MockK
    private lateinit var saveMoviesUseCase: SaveMoviesUseCase

    @MockK
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @MockK
    private lateinit var pageViewModel: PageViewModel

    private  val movieResponse = MovieResponse(1,ArrayList())

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        pageViewModel = spyk(PageViewModel(getMoviesUseCase,saveMoviesUseCase))
    }


    @Test
    fun testGetMoviesAndSaveMoviesWhenIsSuccessful() = testCoroutineRule.runBlockingTest {
        //Arrange
        coEvery { getMoviesUseCase.execute(any()) } returns movieResponse
        //Act
        pageViewModel.getMovies(movieResponse.page)
        //Assert
        coVerify {
            getMoviesUseCase.execute(any())
            saveMoviesUseCase.execute(any(),movieResponse)
        }
        assertEquals(pageViewModel.movies.value,movieResponse.movies)
        assertFalse(pageViewModel.progress.value!!)
    }

    @Test
    fun testGetMoviesWhenIsaException() = testCoroutineRule.runBlockingTest {
        //Arrange
        val error = "error"
        coEvery { getMoviesUseCase.execute(any()) } throws Exception(error)
        //Act
        pageViewModel.getMovies(movieResponse.page)
        //Assert
        assertEquals(pageViewModel.message.value,ERROR_GENERAL_MESSAGE)
    }


}
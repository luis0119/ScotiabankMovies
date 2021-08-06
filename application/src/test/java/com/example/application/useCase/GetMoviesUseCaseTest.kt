package com.example.application.useCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.application.exception.CategoryNotFound
import com.example.application.integration.MovieFactory
import com.example.domain.service.MovieService
import com.example.domain.testDataBuilder.MovieRequestBuilder
import com.example.domain.testDataBuilder.MovieResponseBuilder
import com.example.utilities.Constants
import com.github.testcoroutinesrule.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetMoviesUseCaseTest {

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    lateinit var movieService: MovieService

    @MockK
    lateinit var movieFactory: MovieFactory

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getMoviesUseCase = GetMoviesUseCase(movieFactory,movieService,Dispatchers.Main)
    }

    @Test
    fun testGetMoviesWhenCategoryFound() = testCoroutineRule.runBlockingTest{
        //Arrange
        val movieRequest = MovieRequestBuilder(position = 0,type = "top_rated").build()
        val movieResponse = MovieResponseBuilder().build()
        coEvery { movieFactory.getTypeMovie(0)} returns Optional.of("top_rated")
        coEvery { movieService.getMovies(movieRequest) } returns movieResponse
        //Act
        val result = getMoviesUseCase.execute(movieRequest)
        //Assert
        coVerify {
            movieService.getMovies(movieRequest)
        }
        assertEquals(movieResponse,result)
    }

    //Assert
    @Test(expected = CategoryNotFound::class)
    fun testGetMoviesWhenCategoryNotFound() = testCoroutineRule.runBlockingTest{
        //Arrange
        val movieRequest = MovieRequestBuilder(position = 0,type = "top_rated").build()
        val movieResponse = MovieResponseBuilder().build()
        coEvery { movieFactory.getTypeMovie(0)} returns Optional.ofNullable(null)
        coEvery { movieService.getMovies(movieRequest) } returns movieResponse
        //Act
        getMoviesUseCase.execute(movieRequest)

    }

}
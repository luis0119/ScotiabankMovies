package com.example.domain.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.aggregate.MovieResponse
import com.example.domain.entity.Movie
import com.example.domain.repository.MovieRepository
import com.example.domain.testDataBuilder.MovieBuilder
import com.example.domain.testDataBuilder.MovieRequestBuilder
import com.example.domain.testDataBuilder.MovieResponseBuilder
import com.github.testcoroutinesrule.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieServiceTest {

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var movieService: MovieService

    private val movieRequest = MovieRequestBuilder().build()


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        movieService  = MovieService(movieRepository)
    }

    @Test
    fun testSaveMoviesWhenIsIsSuccessful() = testCoroutineRule.runBlockingTest {
        //Arrange
        val movieResponse = MovieResponseBuilder(movies = ArrayList()).build()

        coEvery { movieRepository.getMoviesDB(movieRequest) } returns movieResponse
        //Act
        movieService.saveMovies(movieRequest,movieResponse)
        //Assert
        coVerify {
            movieRepository.getMoviesDB(movieRequest)
            movieRepository.saveMovies(movieRequest.category,movieResponse)
        }
    }

    @Test
    fun testSaveMoviesWhenIsIsNotSuccessful() = testCoroutineRule.runBlockingTest {
        //Arrange
        val movieResponse = MovieResponseBuilder(movies = getMovies()).build()
        coEvery { movieRepository.getMoviesDB(movieRequest) } returns movieResponse
        //Act
        movieService.saveMovies(movieRequest,movieResponse)
        //Assert
        coVerify(exactly = 0) {
            movieRepository.saveMovies(movieRequest.category,movieResponse)
        }
        coVerify {
            movieRepository.getMoviesDB(movieRequest)
        }
    }

    @Test
    fun testGetMoviesWhenIsSuccessfulToService() = testCoroutineRule.runBlockingTest {
        //Arrange
        val movieResponse = MovieResponseBuilder(movies = getMovies()).build()
        coEvery { movieRepository.getMovies(movieRequest) } returns Response.success(movieResponse)
        //Act
        val result = movieService.getMovies(movieRequest)
        //Assert
        assertEquals(movieResponse,result)
    }

    @Test
    fun testGetMoviesWhenIsSuccessfulToDb() = testCoroutineRule.runBlockingTest {
        //Arrange
        val errorResponse =
            "{\n" +
                    "  \"type\": \"error\",\n" +
                    "  \"message\": \"What you were looking for isn't here.\"\n}"
        val response : Response<MovieResponse> = Response.error(
            400, errorResponse.toResponseBody("application/json".toMediaTypeOrNull()))
        val movieResponse = MovieResponseBuilder(movies = getMovies()).build()
        coEvery { movieRepository.getMovies(movieRequest) } returns response
        coEvery { movieRepository.getMoviesDB(movieRequest) } returns movieResponse
        //Act
        val result = movieService.getMovies(movieRequest)
        //Assert
        assertEquals(movieResponse,result)
    }


    @Test
    fun testGetMoviesGivenAnUnknownHostException() = testCoroutineRule.runBlockingTest {
        //Arrange
        val movieResponse = MovieResponseBuilder(movies = getMovies()).build()
        coEvery { movieRepository.getMoviesDB(movieRequest) } returns movieResponse
        coEvery { movieRepository.getMovies(movieRequest) } throws UnknownHostException("error")
        //Act
        val result = movieService.getMovies(movieRequest)
        //Assert
        assertEquals(movieResponse,result)
    }

    //Assert
    @Test(expected = SocketTimeoutException::class)
    fun testGetMoviesGivenAnException() = testCoroutineRule.runBlockingTest {
        //Arrange
        coEvery { movieRepository.getMovies(movieRequest) } throws SocketTimeoutException("error")
        //Act
         movieService.getMovies(movieRequest)

    }



    private fun getMovies() : List<Movie> {
        val list = ArrayList<Movie>()
        for (i in 1..21){
            val movie = MovieBuilder().build()
            list.add(movie)
        }
        return list
    }


}
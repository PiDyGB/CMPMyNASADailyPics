package com.pidygb.mynasadailypics.pictures

import app.cash.turbine.test
import com.pidygb.mynasadailypics.core.common.Result
import com.pidygb.mynasadailypics.core.data.PicturesRepository
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.pictures.viewmodel.PicturesViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.coVerify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class PicturesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val repository: PicturesRepository = mockk(relaxed = true)
    private lateinit var viewModel: PicturesViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { repository.getPictures() } coAnswers { }
        viewModel = PicturesViewModel(repository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `samples flow emits loading then data`() = runTest(testDispatcher.scheduler) {
        val pictures = listOf(
            Picture("date1", "exp1", "hd1", "image", "v1", "title1", "url1"),
            Picture("date2", "exp2", "hd2", "image", "v1", "title2", "url2")
        )
        coEvery { repository.pictures } returns flowOf(pictures)
        coEvery { repository.getPictures() } coAnswers { }

        viewModel.samples.test {
            assertEquals(Result.Loading, awaitItem())
            testDispatcher.scheduler.runCurrent()
            val result = awaitItem()
            assertIs<Result.Success<List<com.pidygb.mynasadailypics.pictures.model.UiPictureItem>>>(result)
            assertEquals(2, result.data.size)
            assertEquals("title1", result.data[0].title)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `pictureByDate returns correct picture`() = runTest(testDispatcher.scheduler) {
        val pictures = listOf(
            Picture("date1", "exp1", "hd1", "image", "v1", "title1", "url1"),
            Picture("date2", "exp2", "hd2", "image", "v1", "title2", "url2")
        )
        coEvery { repository.pictures } returns flowOf(pictures)

        val picture = viewModel.pictureByDate("date1")

        assertEquals("title1", picture?.title)
    }
}

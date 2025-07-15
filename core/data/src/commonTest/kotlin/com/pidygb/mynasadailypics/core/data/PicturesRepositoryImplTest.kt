package com.pidygb.mynasadailypics.core.data

import app.cash.turbine.test
import com.pidygb.mynasadailypics.core.database.PicturesDatabaseQueries
import com.pidygb.mynasadailypics.core.model.Picture
import com.pidygb.mynasadailypics.core.network.PicturesRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PicturesRepositoryImplTest {

    private val queries: PicturesDatabaseQueries = mockk(relaxed = true)
    private val remoteDataSource: PicturesRemoteDataSource = mockk()

    private val repository = PicturesRepositoryImpl(queries, remoteDataSource)

    @Test
    fun `pictures flow emits pictures from database`() = runTest {
        val pictures = listOf(
            Picture("date1", "exp1", "hd1", "image", "v1", "title1", "url1"),
            Picture("date2", "exp2", "hd2", "image", "v1", "title2", "url2")
        )
        coEvery { queries.selectAllPictures() } returns flowOf(pictures)

        repository.pictures.test {
            assertEquals(pictures, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getPictures fetches from remote and inserts into database`() = runTest {
        val remotePictures = listOf(
            Picture("date3", "exp3", "hd3", "image", "v1", "title3", "url3")
        )
        coEvery { remoteDataSource.getPictures() } returns remotePictures

        repository.getPictures()

        coVerify { queries.resetAllPicturesEntities(remotePictures) }
    }
}

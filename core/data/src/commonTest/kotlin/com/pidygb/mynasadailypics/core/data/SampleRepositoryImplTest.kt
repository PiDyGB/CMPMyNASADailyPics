package com.pidygb.mynasadailypics.core.data

import com.pidygb.mynasadailypics.core.datastore.SampleLocalDataSource
import com.pidygb.mynasadailypics.core.model.Sample
import com.pidygb.mynasadailypics.core.network.SampleRemoteDataSource
import kotlinx.coroutines.flow.flowOf
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SampleRepositoryImplTest {

    private lateinit var localDataSource: SampleLocalDataSource
    private lateinit var remoteDataSource: SampleRemoteDataSource
    private lateinit var repository: SampleRepositoryImpl

    @BeforeTest
    fun setUp() {
        localDataSource = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)
        repository = SampleRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `samples flow should come from localDataSource`() = runTest {
        val expectedSamples = listOf(Sample(id = "1", name = "Test Sample"))
        coEvery { localDataSource.samples } returns flowOf(expectedSamples)

        val actualSamples = repository.samples
        actualSamples.collect {
            assertEquals(expectedSamples, it)
        }
    }

    @Test
    fun `getSamples should fetch from remote and save to local`() = runTest {
        val remoteSamples = listOf(Sample(id = "2", name = "Remote Sample"))
        coEvery { remoteDataSource.getSamples() } returns remoteSamples

        repository.getSamples()

        coVerify { remoteDataSource.getSamples() }
        coVerify { localDataSource.clearAndCreateSamples(remoteSamples) }
    }
}
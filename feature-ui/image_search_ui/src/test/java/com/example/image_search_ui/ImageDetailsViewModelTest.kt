package com.example.image_search_ui

import app.cash.turbine.test
import com.example.flickr_search.repo.FlickrRepo
import com.example.flickr_search.response.Dates
import com.example.flickr_search.response.Owner
import com.example.flickr_search.response.Photo
import com.example.flickr_search.response.PhotoDetailResponse
import com.example.image_search_ui.imagedetailsscreens.ImageDetailMapper
import com.example.image_search_ui.imagedetailsscreens.ImageDetailsViewmodel
import com.example.image_search_ui.imagedetailsscreens.PhotoDetails
import com.example.networking.model.Resource
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ImageDetailsViewModelTest {

    private lateinit var viewModel: ImageDetailsViewmodel
    private val flickrRepo: FlickrRepo = mockk(relaxed = true)
    private val uiMapper: ImageDetailMapper = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when initialized, viewmodel sets imageId and loads details successfully`() = testScope.runTest {
        coEvery { flickrRepo.getImageDetails(any()) } returns Resource.Success(
            data = fakePhotoDetailResponse
        )
        every { uiMapper.mapPhotoDetails(any()) } returns mockPhotoDetails()
        viewModel = ImageDetailsViewmodel(
            imageId = "12345",
            flickrRepo = flickrRepo,
            uiMapper = uiMapper
        )

        viewModel.uiState.test {
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe true
                photoData shouldBe PhotoDetails()
            }
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe false
                photoData shouldBe mockPhotoDetails()
            }
        }
    }

    @Test
    fun `when initialized, viewmodel sets imageId and details fails to load`() = testScope.runTest {
        coEvery { flickrRepo.getImageDetails(any()) } returns Resource.Error(responseCode = null)
        every { uiMapper.mapPhotoDetails(any()) } returns mockPhotoDetails()
        viewModel = ImageDetailsViewmodel(
            imageId = "12345",
            flickrRepo = flickrRepo,
            uiMapper = uiMapper
        )

        viewModel.uiState.test {
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe true
                photoData shouldBe PhotoDetails()
            }
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe true
                photoData shouldBe PhotoDetails()
                loadDetailFailed shouldBe true
            }
        }
    }

    @Test
    fun `when initialized, viewmodel sets imageId and images fails to load`() = testScope.runTest {
        coEvery { flickrRepo.getImageDetails(any()) } returns Resource.Success(
            data = fakePhotoDetailResponse
        )
        coEvery { flickrRepo.fetchImages(any(),any()) } returns Resource.Error(responseCode = null)
        every { uiMapper.mapPhotoDetails(any()) } returns mockPhotoDetails()
        viewModel = ImageDetailsViewmodel(
            imageId = "12345",
            flickrRepo = flickrRepo,
            uiMapper = uiMapper
        )

        viewModel.uiState.test {
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe true
                photoData shouldBe PhotoDetails()
            }
            with(awaitItem()) {
                imageId shouldBe "12345"
                loading shouldBe false
                photoData shouldBe mockPhotoDetails()
            }
            with(awaitItem()) {
                imageId shouldBe "12345"
                loadImagesFailed shouldBe true
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object {
        val fakePhotoDetailResponse = PhotoDetailResponse(
            photo = Photo(
                id = "23",
                owner = Owner(
                    nsid = "mel",
                    username = "Keith Rice",
                    iconserver = "quam",
                    iconfarm = 4246
                ),
                secret = "senserit",
                server = "velit",
                farm = 3926,
                dates = Dates(taken = "honestatis")
            )
        )

        val fakePhoto = Photo(
            id = "23",
            owner = Owner(
                nsid = "mel",
                username = "Keith Rice",
                iconserver = "quam",
                iconfarm = 4246
            ),
            secret = "senserit",
            server = "velit",
            farm = 3926,
            dates = Dates(taken = "honestatis")
        )

        private fun mockPhotoDetails() = PhotoDetails(
            id = "12345",
            url = "https://example.com/photo.jpg",
            ownerId = "owner_123",
            ownerIconUrl = "https://example.com/icon.jpg",
            ownerName = "Test User",
            dateTaken = "2024-03-10"
        )

        private fun mockMappedPhotoDetails() = PhotoDetails(
            id = "12345",
            url = "https://example.com/photo.jpg",
            ownerId = "owner_123",
            ownerIconUrl = "https://example.com/icon.jpg",
            ownerName = "Test User",
            dateTaken = "2024-03-10"
        )
    }
}


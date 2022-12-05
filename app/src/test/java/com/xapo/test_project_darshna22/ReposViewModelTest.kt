package com.xapo.test_project_darshna22

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.xapo.test_project_darshna22.FakeData.DEFAULT_LANGUAGE
import com.xapo.test_project_darshna22.data.model.Repo
import com.xapo.test_project_darshna22.repository.GithubRepository
import com.xapo.test_project_darshna22.ui.repos.ReposViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReposViewModelTest {

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: ReposViewModel

    @Mock
    private lateinit var repository: GithubRepository

    private val observer: Observer<PagingData<Repo>> = mock()

    @Captor
    private lateinit var captor: ArgumentCaptor<PagingData<Repo>>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = ReposViewModel(repository)
        viewModel.repos.observeForever(observer)

    }

    @Test
    fun `when search result is called, livedata value is set`() = runBlockingTest {

        val expectedResult = PagingData.from(listOf(FakeData.getRepo()))

        val liveDataResult = MutableLiveData<PagingData<Repo>>(expectedResult)

        whenever(repository.getSearchResults(DEFAULT_LANGUAGE)).thenAnswer {
            liveDataResult
        }

        viewModel.searchRepos(DEFAULT_LANGUAGE)

        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedResult, value)
        }

    }

    @Test
    fun `when search result is null, livedata value is set`() = runBlockingTest {

        val expectedResult = null
        
        whenever(repository.getSearchResults(DEFAULT_LANGUAGE)).thenAnswer {
            MutableLiveData(null)
        }

        viewModel.searchRepos(DEFAULT_LANGUAGE)

        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedResult, value)
        }

    }

}
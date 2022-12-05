package com.xapo.test_project_darshna22

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.xapo.test_project_darshna22.FakeData.DEFAULT_LANGUAGE
import com.xapo.test_project_darshna22.base.api.GithubApi
import com.xapo.test_project_darshna22.data.GithubPagingSource
import com.xapo.test_project_darshna22.data.model.Repo
import com.xapo.test_project_darshna22.data.model.RepoSearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReviewsPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var api: GithubApi
    lateinit var reviewsPagingSource: GithubPagingSource

    companion object {
        val reviewsResponse = RepoSearchResponse(
            items = listOf(
                FakeData.getRepo()
            ),
            total = 10,
            nextPage = 1
        )
        val nextRepoResponse = RepoSearchResponse(
            items = listOf(
                FakeData.getRepo()
            ),
            total = 10,
            nextPage = 1
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        reviewsPagingSource = GithubPagingSource(api, DEFAULT_LANGUAGE)
    }

    @Test
    fun `reviews paging source load - failure - http error`() = runBlockingTest {
        val error = RuntimeException("404", Throwable())
        given(api.getTrendingRepos(DEFAULT_LANGUAGE, 0, 1)).willThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, Repo>(error)

        val actualResult = reviewsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, actualResult
        )
    }

    @Test
    fun `reviews paging source load - failure - received null`() = runBlockingTest {
        given(api.getTrendingRepos(DEFAULT_LANGUAGE, 0, 1)).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, Repo>(NullPointerException())
        val actualResult = reviewsPagingSource.load(
            PagingSource.LoadParams.Prepend(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult.toString(), actualResult.toString()
        )
    }

    @Test
    fun `reviews paging source refresh - success`() = runBlockingTest {
        given(api.getTrendingRepos(DEFAULT_LANGUAGE, 0, 1)).willReturn(nextRepoResponse)

        val expectedResult = PagingSource.LoadResult.Page(
            data = reviewsResponse.items.map { it },
            prevKey = -1,
            nextKey = 1
        )
        val actualResult = reviewsPagingSource.load(
            PagingSource.LoadParams.Prepend(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult, actualResult
        )
    }

    @Test
    fun `reviews paging source append - success`() = runBlockingTest {
        given(api.getTrendingRepos(DEFAULT_LANGUAGE, 0, 1)).willReturn(nextRepoResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = reviewsResponse.items.map { it },
            prevKey = -1,
            nextKey = 1
        )
        val actualResult = reviewsPagingSource.load(
            PagingSource.LoadParams.Append(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult,
            actualResult
        )
    }

    @Test
    fun `reviews paging source prepend - success`() = runBlockingTest {
        given(api.getTrendingRepos(DEFAULT_LANGUAGE, 0, 1)).willReturn(nextRepoResponse)
        val expectedResult = PagingSource.LoadResult.Page(
            data = reviewsResponse.items.map { it },
            prevKey = -1,
            nextKey = 1
        )
        val actualResult = reviewsPagingSource.load(
            PagingSource.LoadParams.Prepend(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(
            expectedResult,
            actualResult
        )
    }

}
package com.xapo.test_project_darshna22.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.xapo.test_project_darshna22.data.GithubPagingSource
import com.xapo.test_project_darshna22.base.api.GithubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(private val githubApi: GithubApi):GithubRepository {

    override fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(githubApi, query) }
        ).liveData
}
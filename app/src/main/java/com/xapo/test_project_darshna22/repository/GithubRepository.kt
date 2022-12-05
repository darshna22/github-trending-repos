package com.xapo.test_project_darshna22.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.xapo.test_project_darshna22.data.model.Repo

interface GithubRepository {
    fun getSearchResults(query: String):LiveData<PagingData<Repo>>
}
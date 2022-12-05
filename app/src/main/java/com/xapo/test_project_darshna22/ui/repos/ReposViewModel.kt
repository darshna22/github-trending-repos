package com.xapo.test_project_darshna22.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xapo.test_project_darshna22.data.model.Repo
import com.xapo.test_project_darshna22.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val repos: LiveData<PagingData<Repo>> = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString)
    }

    fun searchRepos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "language:Kotlin"
    }

}
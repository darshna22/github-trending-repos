package com.xapo.test_project_darshna22.base.api

import com.xapo.test_project_darshna22.data.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories?sort=stars")
    suspend fun getTrendingRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}
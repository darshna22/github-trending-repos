package com.xapo.test_project_darshna22.data.model

import com.google.gson.annotations.SerializedName
import com.xapo.test_project_darshna22.data.model.Repo

data class RepoSearchResponse(

    @SerializedName("total_count")
    val total: Int = 0,

    @SerializedName("items")
    val items: List<Repo> = emptyList(),

    val nextPage: Int? = null
)
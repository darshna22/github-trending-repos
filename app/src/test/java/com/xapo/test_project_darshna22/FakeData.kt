package com.xapo.test_project_darshna22

import com.xapo.test_project_darshna22.data.model.Owner
import com.xapo.test_project_darshna22.data.model.Repo
import com.xapo.test_project_darshna22.data.model.RepoSearchResponse

object FakeData {
    const val DEFAULT_LANGUAGE="language:Kotlin"

     fun getRepo(): Repo {
        return Repo(
            5152285,
            "okhttp",
            "square/okhttp",

            "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",

            "https://github.com/square/okhttp",

            43233,

            8977,

            "kotlin",

            43233,

            Owner(
                2,
                "j",
                ""
            ),

            "",

            "",

            1

        )
    }

}
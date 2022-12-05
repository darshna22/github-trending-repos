package com.xapo.test_project_darshna22.base.di

import com.xapo.test_project_darshna22.repository.GithubRepository
import com.xapo.test_project_darshna22.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesGithubRepository(impl: GithubRepositoryImpl): GithubRepository

}
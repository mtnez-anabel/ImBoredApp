package com.anabelmm.imboredapp.model.di

import com.anabelmm.imboredapp.model.Repository
import com.anabelmm.imboredapp.model.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getRepository(repository: RepositoryImp): Repository
}
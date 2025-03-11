package com.example.articapp.di

import com.example.articapp.data.network.ArticRepositoryImpl
import com.example.articapp.domain.api.ArticRepository
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
    abstract fun bindArticRepository(articRepository: ArticRepositoryImpl): ArticRepository

}
package com.example.articapp.di

import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.impl.ArticInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun bindArticInteractor(articInteractor: ArticInteractorImpl): ArticInteractor

}
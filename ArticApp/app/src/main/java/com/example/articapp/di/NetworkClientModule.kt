package com.example.articapp.di

import com.example.articapp.data.NetworkClient
import com.example.articapp.data.network.ArticRetrofitNetworkClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkClientModule {

    @Binds
    @Singleton
    abstract fun provideArticRetrofitNetworkClient(networkClient: ArticRetrofitNetworkClient): NetworkClient

}
package com.example.articapp.utils

import android.content.Context
import com.example.articapp.data.NetworkClient
import com.example.articapp.data.network.ArticRepositoryImpl
import com.example.articapp.data.network.RetrofitNetworkClient
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.api.ArticRepository
import com.example.articapp.domain.impl.ArticInteractorImpl

object Creator {

    private fun getNetworkClient(): NetworkClient {
        return RetrofitNetworkClient()
    }

    private fun getArticRepository(): ArticRepository {
        return ArticRepositoryImpl(getNetworkClient())
    }

    fun getArticInteractor(): ArticInteractor {
        return ArticInteractorImpl(getArticRepository())
    }

}
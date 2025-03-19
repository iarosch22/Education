package com.example.articapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.ui.models.ArticState
import com.example.articapp.utils.MessageType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsListViewModel @Inject constructor(private val articInteractor: ArticInteractor) : ViewModel() {

    private var page: Int = 0

    private var isLoading = false

    private val artworks = mutableListOf<ArtWorkEntity>()

    private val stateLiveData = MutableLiveData<ArticState>(ArticState.Loading)
    fun observeState(): LiveData<ArticState> = stateLiveData

    init {
        getArtworks()
    }

    fun getArtworks() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                articInteractor
                    .getArtworks(++page)
                    .collect{ searchResults ->
                        processResult(
                            searchResults.artWorks,
                            searchResults.messageType,
                            searchResults.errorCode
                        )
                        isLoading = false
                    }
            } catch (e: Throwable) {
                processResult(null, MessageType.UNKNOWN_ERROR, "400")
                isLoading = false
            }
        }
    }

    private fun processResult(
        foundedArtworks: List<ArtWorkEntity>?,
        error: MessageType?,
        errorCode: String
    ) {
        if (foundedArtworks != null) {
            artworks.addAll(foundedArtworks)
        }

        when {
            error != null -> renderState(ArticState.Error(
                messageType = error,
                errorCode = errorCode
            ))
            foundedArtworks!!.isEmpty() && page > 1 -> {
                renderState(ArticState.Error(
                    messageType = MessageType.END_OF_CONTENT
                ))
            }
            else -> {
                renderState(ArticState.Content(artworks = artworks))
            }
        }
    }

    private fun renderState(state: ArticState) {
        stateLiveData.postValue(state)
    }

}
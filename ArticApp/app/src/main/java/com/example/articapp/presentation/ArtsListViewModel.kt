package com.example.articapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.presentation.ui.models.ArticState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsListViewModel @Inject constructor(private val articInteractor: ArticInteractor) : ViewModel() {


    //Для теста
    private var page: Int = 6395

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
                            searchResults.errorCode,
                            searchResults.totalPages
                        )
                        isLoading = false
                    }
            } catch (e: Throwable) {
                val artworks = articInteractor.getArtworksFromDb()
                processResult(
                    foundedArtworks = artworks,
                    error = null,
                    totalPages = null
                )
                isLoading = false
            }
        }
    }

    private fun processResult(
        foundedArtworks: List<ArtWorkEntity>?,
        error: MessageType?,
        errorCode: String = "",
        totalPages: Int?
    ) {

        Log.d("TOTAL_PAGE", totalPages.toString())

        if (foundedArtworks != null) {
            artworks.addAll(foundedArtworks)
        }

        when {
            error != null -> renderState(
                ArticState.Error(
                messageType = error,
                errorCode = errorCode
            ))
            totalPages != null && page > totalPages -> {
                renderState(
                    ArticState.Error(
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
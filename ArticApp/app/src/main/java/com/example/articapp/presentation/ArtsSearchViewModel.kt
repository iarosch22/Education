package com.example.articapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.ui.models.ArticState
import com.example.articapp.utils.ErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtsSearchViewModel(private val articInteractor: ArticInteractor): ViewModel() {

    private val stateLiveData = MutableLiveData<ArticState>()
    fun observeState(): LiveData<ArticState> = stateLiveData

    fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    articInteractor
                        .searchArtworks(newSearchText)
                        .collect { searchResults ->
                            processResult(searchResults.artWorks, searchResults.errorType)
                        }
                } catch (e: Throwable) {
                    processResult(null, ErrorType.UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun processResult(foundArtworks: List<ArtWorkEntity>?, errorType: ErrorType?) {
        val artworks = mutableListOf<ArtWorkEntity>()

        if (foundArtworks != null) artworks.addAll(foundArtworks)

        when {
            errorType != null -> renderState(ArticState.Error(errorType = errorType))
            artworks.isEmpty() -> renderState(ArticState.Error(errorType = ErrorType.DATABASE_ERROR))
            else -> renderState(ArticState.Content(artworks = artworks))
        }
    }

    private fun renderState(state: ArticState) {
        stateLiveData.postValue(state)
    }

    companion object {
        fun getViewModelFactory(articInteractor: ArticInteractor): ViewModelProvider.Factory = viewModelFactory {
                initializer {
                    ArtsSearchViewModel(articInteractor)
                }
        }
    }

}
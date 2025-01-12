package com.example.articapp.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.models.ArtWork
import com.example.articapp.ui.models.ArticState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtsSearchViewModel(private val articInteractor: ArticInteractor): ViewModel() {

    private val stateLiveData = MutableLiveData<ArticState>()
    fun observeState(): LiveData<ArticState> = stateLiveData

    fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            viewModelScope.launch {
                articInteractor
                    .searchArtworks(newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundArtworks: List<ArtWork>?, errorMessage: String?) {
        val artworks = mutableListOf<ArtWork>()

        if (foundArtworks != null) artworks.addAll(foundArtworks)

        when {
            errorMessage != null -> renderState(ArticState.Error(errorMessage = errorMessage))
            artworks.isEmpty() -> renderState(ArticState.Empty(message = "Ничего не нашлось"))
            else -> renderState(ArticState.Content(artworks = artworks))
        }
    }

    private fun renderState(state: ArticState) {
        stateLiveData.postValue(state)
    }

}
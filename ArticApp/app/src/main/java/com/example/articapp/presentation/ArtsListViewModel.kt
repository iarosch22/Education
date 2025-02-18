package com.example.articapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articapp.domain.api.ArticInteractor
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.ui.models.ArticState
import com.example.articapp.utils.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsListViewModel @Inject constructor(private val articInteractor: ArticInteractor) : ViewModel() {

    private val stateLiveData = MutableLiveData<ArticState>(ArticState.Loading)
    fun observeState(): LiveData<ArticState> = stateLiveData

    init {
        getArtworks()
    }

    private fun getArtworks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                articInteractor
                    .getArtworks()
                    .collect{ searchResults ->
                        processResult(searchResults.artWorks, searchResults.errorType)
                    }
            } catch (e: Throwable) {
                processResult(null, ErrorType.UNKNOWN_ERROR)
            }
        }
    }

    private fun processResult(foundedArtworks: List<ArtWorkEntity>?, error: ErrorType?) {
        val artworks = mutableListOf<ArtWorkEntity>()

        if (foundedArtworks != null) artworks.addAll(foundedArtworks)

        when {
            error != null -> renderState(ArticState.Error(error))
            else -> renderState(ArticState.Content(artworks = artworks))
        }
    }

    private fun renderState(state: ArticState) {
        stateLiveData.postValue(state)
    }

}
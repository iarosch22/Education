package com.example.articapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.articapp.R
import com.example.articapp.databinding.FragmentArtslistBinding
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.presentation.ArtsListViewModel
import com.example.articapp.ui.models.ArticState
import com.example.articapp.utils.ErrorType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsListFragment: Fragment() {

    private var _binding: FragmentArtslistBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ArtsListAdapter() }

    private val viewModel: ArtsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvArtworks.adapter = adapter

        viewModel.observeState().observe(viewLifecycleOwner){
            when(it) {
                is ArticState.Content -> {
                    showContent(it.artworks)
                }
                is ArticState.Error -> showMessage(errorType = it.errorType, errorCode = it.errorCode)
                ArticState.Loading -> showLoading()
            }
        }
    }

    private fun showContent(foundedArtworks: List<ArtWorkEntity>) {
        binding.progressBar.visibility = View.GONE
        binding.rvArtworks.visibility = View.VISIBLE
        binding.phMessage.visibility = View.GONE

        adapter.submitList(foundedArtworks)
    }

    private fun showMessage(errorType: ErrorType, errorCode: String) {
        binding.progressBar.visibility = View.GONE
        binding.rvArtworks.visibility = View.GONE
        binding.phMessage.visibility = View.VISIBLE
        when(errorType) {
            ErrorType.NETWORK_ERROR -> {
                binding.phMessage.text = getString(R.string.app_error_network)
            }
            ErrorType.UNKNOWN_ERROR -> {
                val text = "${getString(R.string.app_error_unknown)} $errorCode"
                binding.phMessage.text = text
            }
            ErrorType.DATABASE_ERROR -> {
                binding.phMessage.text = getString(R.string.app_error_database)
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvArtworks.visibility = View.GONE
        binding.phMessage.visibility = View.GONE
    }

}
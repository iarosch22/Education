package com.example.articapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.articapp.databinding.FragmentArtslistBinding
import com.example.articapp.domain.models.ArtWorkEntity
import com.example.articapp.presentation.ArtsListViewModel
import com.example.articapp.ui.models.ArticState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsListFragment: Fragment() {

    private var _binding: FragmentArtslistBinding? = null
    private val binding get() = _binding!!

    private val artworks = mutableListOf<ArtWorkEntity>()

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

        adapter.artworks = artworks
        binding.rvArtworks.adapter = adapter

        viewModel.observe().observe(viewLifecycleOwner){
            when(it) {
                is ArticState.Content -> {
                    Log.d("ARTWORKS_CONTENT", it.artworks.first().imageUrl)
                    showContent(it.artworks)
                }
                is ArticState.Error -> showMessage()
            }
        }
    }

    private fun showContent(foundedArtworks: List<ArtWorkEntity>) {
        binding.rvArtworks.visibility = View.VISIBLE
        binding.phMessage.visibility = View.GONE

        artworks.clear()
        artworks.addAll(foundedArtworks)
        adapter.notifyDataSetChanged()
    }

    private fun showMessage() {
        binding.rvArtworks.visibility = View.GONE
        binding.phMessage.visibility = View.VISIBLE
    }

}
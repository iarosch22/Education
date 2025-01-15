package com.example.articapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.articapp.databinding.FragmentArtssearchBinding
import com.example.articapp.presentation.ArtsSearchViewModel
import com.example.articapp.ui.models.ArticState
import com.example.articapp.utils.Creator

class ArtsSearchFragment: Fragment() {

    private var _binding: FragmentArtssearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ArtsSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtssearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ArtsSearchViewModel.getViewModelFactory(Creator.getArticInteractor()))[ArtsSearchViewModel::class.java]
        viewModel.observeState().observe(viewLifecycleOwner) {
            when(it) {
                is ArticState.Content -> {
                    Toast.makeText(requireContext(), "${it.artworks.first()}", Toast.LENGTH_SHORT).show()
                }
                is ArticState.Empty -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is ArticState.Error -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnArtsSearch.setOnClickListener {
            if (binding.queryInput.text.isNotEmpty()) viewModel.searchRequest(binding.queryInput.text.toString())
        }
    }

}
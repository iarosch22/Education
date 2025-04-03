package com.example.articapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.articapp.R
import com.example.articapp.databinding.FragmentArtssearchBinding
import com.example.articapp.presentation.ArtsSearchViewModel
import com.example.articapp.presentation.ui.models.ArticState
import com.example.articapp.presentation.MessageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtsSearchFragment: Fragment() {

    private var _binding: FragmentArtssearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtsSearchViewModel by viewModels()

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

        viewModel.observeState().observe(viewLifecycleOwner) {
            when(it) {
                is ArticState.Content -> {
                    Toast.makeText(requireContext(), "${it.artworks.first()}", Toast.LENGTH_SHORT).show()
                }
                is ArticState.Error -> {
                    when(it.messageType) {
                        MessageType.NETWORK_ERROR -> Toast.makeText(requireContext(),
                            getString(R.string.app_error_network), Toast.LENGTH_SHORT).show()
                        MessageType.DATABASE_ERROR -> Toast.makeText(requireContext(),
                            getString(R.string.app_error_database), Toast.LENGTH_SHORT).show()
                        MessageType.UNKNOWN_ERROR -> Toast.makeText(requireContext(),
                            getString(R.string.app_error_unknown), Toast.LENGTH_SHORT).show()
                        MessageType.END_OF_CONTENT -> Toast.makeText(requireContext(),
                            getString(R.string.app_end_of_content), Toast.LENGTH_SHORT).show()
                    }
                }

                else -> {}
            }
        }

        binding.btnArtsSearch.setOnClickListener {
            viewModel.searchRequest(binding.queryInput.text.toString())
        }
    }

}
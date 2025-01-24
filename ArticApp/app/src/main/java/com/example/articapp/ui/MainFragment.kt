package com.example.articapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.articapp.R
import com.example.articapp.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnArtsList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_artsListFragment)
        }

        binding.btnArtsSearch.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_artsSearchFragment)
        }
    }

}
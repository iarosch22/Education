package com.example.articapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.articapp.R
import com.example.articapp.domain.models.ArtWorkEntity

class ArtsListAdapter: ListAdapter<ArtWorkEntity, ArtsListViewHolder>(ArtworkDiffCallback()) {

    //var artworks = mutableListOf<ArtWorkEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_artwork_item, parent, false)
        return ArtsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
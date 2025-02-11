package com.example.articapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.articapp.R
import com.example.articapp.domain.models.ArtWorkEntity

class ArtsListAdapter: RecyclerView.Adapter<ArtsListViewHolder>() {

    var artworks = mutableListOf<ArtWorkEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_artwork_item, parent, false)
        return ArtsListViewHolder(view)
    }

    override fun getItemCount(): Int = artworks.size

    override fun onBindViewHolder(holder: ArtsListViewHolder, position: Int) {
        holder.bind(artworks[position])
    }

}
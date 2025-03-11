package com.example.articapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.articapp.domain.models.ArtWorkEntity

class ArtworkDiffCallback: DiffUtil.ItemCallback<ArtWorkEntity>() {

    override fun areItemsTheSame(oldItem: ArtWorkEntity, newItem: ArtWorkEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArtWorkEntity, newItem: ArtWorkEntity): Boolean {
        return oldItem == newItem
    }

}
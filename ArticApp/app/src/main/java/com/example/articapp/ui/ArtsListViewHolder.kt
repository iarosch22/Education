package com.example.articapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.articapp.R
import com.example.articapp.domain.models.ArtWorkEntity

class ArtsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val ivArtwork: ImageView = itemView.findViewById(R.id.ivArtwork)
    private val tvArtwork: TextView = itemView.findViewById(R.id.tvTitle)

    fun bind(item: ArtWorkEntity) {
        tvArtwork.text = item.title

        Glide.with(itemView)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(ivArtwork)
    }


}
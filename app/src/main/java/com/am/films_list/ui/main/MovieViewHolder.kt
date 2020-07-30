package com.am.films_list.ui.main

import android.view.View
import com.am.films_list.common.SimpleViewHolder
import com.am.films_list.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class MovieViewHolder(
    itemView: View,
    private val handler: (id: Long) -> Unit
) : SimpleViewHolder<MovieItemData>(itemView) {

    private val binding = ItemMovieBinding.bind(itemView)

    override fun bind(data: MovieItemData) {
        setListener(data)
        loadImage(data)
        binding.nameTextView.text = data.name
    }

    private fun setListener(data: MovieItemData) {
        itemView.setOnClickListener {
            handler.invoke(data.id)
        }
    }

    private fun loadImage(data: MovieItemData) {
        Glide.with(itemView.context)
            .load(data.imageUrl)
            .into(binding.movieImageView)
    }
}
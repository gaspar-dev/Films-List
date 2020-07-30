package com.am.films_list.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.am.films_list.R
import com.am.films_list.ui.State
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        fetchData(arguments?.getLong("MovieId"))
    }

    private fun fetchData(id: Long?) {
        if (id == null) return
        viewModel.getMovieDetail(id)
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.OnSuccess -> updateUi(it.data)
                is State.OnError -> showErrorToast()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(it: MovieDetail) {
        loadImage(it.imageUrl)
        budgetTextView.text = "${it.budget}$"
        nameTextView.text = it.title
        language.text = "Language: ${it.originalLanguage}"
    }

    private fun loadImage(url: String) {
        Glide.with(requireContext())
            .load(url)
            .into(movieDetailImageView)
    }

    private fun showErrorToast() {
        Toast.makeText(context, "Something Wrong", Toast.LENGTH_LONG).show()
    }

}
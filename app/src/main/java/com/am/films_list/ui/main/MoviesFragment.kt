package com.am.films_list.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.films_list.R
import com.am.films_list.common.EndlessScrollListener
import com.am.films_list.common.SimpleRecyclerViewAdapter
import com.am.films_list.ui.State
import com.am.films_list.utils.gone
import com.am.films_list.utils.show
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val viewModel by viewModel<MoviesViewModel>()
    private lateinit var simpleMovieAdapter: SimpleRecyclerViewAdapter<MovieItemData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        simpleMovieAdapter = SimpleRecyclerViewAdapter(R.layout.item_movie) { view ->
            MovieViewHolder(view, viewModel::onItemClicked)
        }
        with(moviesRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = simpleMovieAdapter
            addOnScrollListener(EndlessScrollListener {
                if (viewModel.state.value is State.Loading || viewModel.isComplete) {
                    return@EndlessScrollListener
                }
                viewModel.loadNextMovies()
            })
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> progressBar.show()
                is State.OnSuccess -> {
                    progressBar.gone()
                    simpleMovieAdapter.addAll(it.data)
                }
                is State.OnError -> {
                    progressBar.gone()
                    showErrorToast()
                }
            }
        })
        viewModel.detail.observe(viewLifecycleOwner, Observer {
            navigateToDetailFragment(it)
        })
    }

    private fun navigateToDetailFragment(id: Long) {
        val bundle = bundleOf("MovieId" to id)
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailFragment, bundle)
    }

    private fun showErrorToast() {
        Toast.makeText(context, "Something Wrong", Toast.LENGTH_LONG).show()
    }
}
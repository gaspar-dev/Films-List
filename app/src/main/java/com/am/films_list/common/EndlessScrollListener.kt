package com.am.films_list.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    private val handle: () -> Unit
) : RecyclerView.OnScrollListener() {
    private val visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy != 0) {
            val lastVisibleItem: Int
            val itemsCount: Int
            if (recyclerView.layoutManager !is LinearLayoutManager) {
                return
            }
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            itemsCount = layoutManager.itemCount

            if (lastVisibleItem + visibleThreshold >= itemsCount) {
                handle()
            }
        }
    }
}
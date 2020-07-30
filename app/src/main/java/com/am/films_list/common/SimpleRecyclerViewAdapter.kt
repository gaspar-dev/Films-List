package com.am.films_list.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

typealias ViewHolderBuilder<T> = (view: View) -> SimpleViewHolder<T>

class SimpleRecyclerViewAdapter<T>(
    private var itemResId: Int,
    private var viewHolderBuilder: ViewHolderBuilder<T>
) : RecyclerView.Adapter<SimpleViewHolder<T>>() {

    private var dataList: MutableList<T> = mutableListOf()

    fun addAll(dataList: List<T>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setData(dataList: List<T>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun clear() {
        dataList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(itemResId, parent, false)
        return viewHolderBuilder(itemView)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

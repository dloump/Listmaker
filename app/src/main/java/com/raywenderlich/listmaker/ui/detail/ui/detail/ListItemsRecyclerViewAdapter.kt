package com.raywenderlich.listmaker.ui.detail.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup

class ListItemsRecyclerViewAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ListItemViewHolder {
        val binding =
            ListItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: int) {
        holder.binding.textViewTask.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

}
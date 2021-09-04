package com.raywenderlich.listmaker.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

val listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")

//inheriting from recycler view
class ListSelectionRecyclerViewAdapter :
    RecyclerView.Adapter<ListSelectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }
}
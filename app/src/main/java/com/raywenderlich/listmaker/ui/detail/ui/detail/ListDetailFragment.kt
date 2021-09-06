package com.raywenderlich.listmaker.ui.detail.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.MainActivity
import com.raywenderlich.listmaker.R
import com.raywenderlich.listmaker.TaskList
import com.raywenderlich.listmaker.databinding.ListDetailFragmentBinding
import com.raywenderlich.listmaker.ui.detail.R
import com.raywenderlich.listmaker.ui.main.MainViewModel
import com.raywenderlich.listmaker.ui.main.MainViewModelFactory

lateinit var binding: ListDetailFragmentBinding

class ListItemsRecyclerViewAdapter(var list: TaskList) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: ListDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View {
        //android generated binding to layout for fragment
        binding = ListDetailFragmentBinding.inflate(inflater,
            container, false)
        //returning root of the View for the fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(
                PreferenceManager.getDefaultSharedPreferences(requireActivity()))
        )
            .get(MainViewModel::class.java)
        val recyclerAdapter =
            ListItemsRecyclerViewAdapter(viewModel.list)
        binding.listItemsRecyclerview.adapter = recyclerAdapter
        binding.listItemsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext())
        viewModel.onTaskAdded = {
            recyclerAdapter.notifyDataSetChanged()
        }

        val list: TaskList? =
            arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)
        if (list != null) {
            viewModel.list = list
            requireActivity().title = list.name
        }
    }

}
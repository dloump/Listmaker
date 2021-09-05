package com.raywenderlich.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//adding constructor to receive instance of SharedPreferences
//& implementing interface
class MainViewModelFactory(private val sharedPreferences:
                           SharedPreferences
) : ViewModelProvider.Factory {
    //overriding & returning instance of MainViewModel that uses SharedPreferences
    //field in constructor
    override fun <T : ViewModel?> create(modelClass: Class<T>):
            T {
        return MainViewModel(sharedPreferences) as T
    }}

package com.raywenderlich.listmaker.ui.detail

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.raywenderlich.listmaker.MainActivity
import com.raywenderlich.listmaker.R
import com.raywenderlich.listmaker.databinding.ListDetailActivityBinding
import com.raywenderlich.listmaker.ui.detail.ui.detail.ListDetailFragment
import com.raywenderlich.listmaker.ui.detail.ui.detail.ListDetailViewModel
import com.raywenderlich.listmaker.ui.main.MainViewModel
import com.raywenderlich.listmaker.ui.main.MainViewModelFactory

class ListDetailActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var fragment: ListDetailFragment
    lateinit var binding: ListDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

        viewModel = ViewModelProvider(
            this,

            MainViewModelFactory(
                PreferenceManager.getDefaultSharedPreferences(this))
        ).get(MainViewModel::class.java)

        //referencing list in the Intent & assigning it to list variable
        viewModel.list =
            intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        //assigning title of activity to name of list
        title = viewModel.list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    private fun showCreateTaskDialog() {
        //creating EditText to receive text from User
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        //setting up alert dialog
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                //accessing EditText to create task from text input
                val task = taskEditText.text.toString()
                //notifying ViewModel that new item was added
                viewModel.addTask(task)
                //closing dialog
                dialog.dismiss()
            }
            //creating & showing alert dialog
            .create()
            .show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,
            viewModel.list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

}
package com.eldroid.bottomnavigation.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.eldroid.bottomnavigation.R
import com.eldroid.bottomnavigation.adapter.TaskAdapter
import com.eldroid.bottomnavigation.data.Task

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoListFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: ArrayList<Task>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)

        // Initialize the task list
        taskList = ArrayList()

        // Set up the ListView and the adapter
        val listViewTasks = view.findViewById<ListView>(R.id.listViewTasks)
        taskAdapter = TaskAdapter(requireContext(), taskList)
        listViewTasks.adapter = taskAdapter

        // Handle adding tasks
        val editTextTask = view.findViewById<EditText>(R.id.editTextTask)
        val buttonAddTask = view.findViewById<Button>(R.id.buttonAddTask)

        // Set hint text color programmatically
        editTextTask.setHintTextColor(resources.getColor(R.color.white, null))

        buttonAddTask.setOnClickListener {
            val taskDescription = editTextTask.text.toString()
            if (taskDescription.isNotEmpty()) {
                val newTask = Task(taskDescription)
                taskList.add(newTask)
                taskAdapter.notifyDataSetChanged() // Refresh the list
                editTextTask.text.clear() // Clear the input field
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToDoListFragment()
    }
}

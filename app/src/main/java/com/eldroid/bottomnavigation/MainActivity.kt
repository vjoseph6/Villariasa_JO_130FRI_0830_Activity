package com.eldroid.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eldroid.bottomnavigation.Fragment.HomeFragmentCalc
import com.eldroid.bottomnavigation.Fragment.ProfileFragment
import com.eldroid.bottomnavigation.Fragment.ToDoListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Load the ToDoListFragment as the default fragment
        if (savedInstanceState == null) {
            // Load the ToDoListFragment instead of the HomeFragmentCalc
            loadFragment(ToDoListFragment())
            // Set the ToDoList tab as selected
            bottomNavigationView.selectedItemId = R.id.toDoList
        }

        // Set the item selected listener
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragmentCalc())
                    true
                }

                R.id.toDoList -> {
                    loadFragment(ToDoListFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
package com.eldroid.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment

import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initial fragment if needed
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment.newInstance())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_first -> {
                // Navigate to FirstFragment
                loadFragment(FirstFragment.newInstance())
                true
            }
            R.id.menu_dialog -> {
                // Show DialogFragment
                YourDialogFragment.newInstance().show(supportFragmentManager, "dialog")
                true
            }
            R.id.menu_more_first -> {
                // Navigate to FirstFragment
                loadFragment(FirstFragment.newInstance())
                true
            }
            R.id.menu_more_dialog -> {
                // Show DialogFragment
                YourDialogFragment.newInstance().show(supportFragmentManager, "dialog")
                true
            }
            R.id.menu_exit -> {
                // Exit the app
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

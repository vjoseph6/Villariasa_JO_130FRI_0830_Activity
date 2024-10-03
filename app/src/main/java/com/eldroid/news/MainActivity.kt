package com.eldroid.news

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.eldroid.news.Fragment.HeadlineListFragment
import com.eldroid.news.Fragment.NewsContentFragment

class MainActivity : AppCompatActivity() {

    private var selectedHeadline: String? = null
    private var selectedContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restore saved headline and content if available
        if (savedInstanceState != null) {
            selectedHeadline = savedInstanceState.getString("selectedHeadline")
            selectedContent = savedInstanceState.getString("selectedContent")
        }

        if (savedInstanceState == null) {
            // Initially load the HeadlineFragment into the left container
            loadFragment(HeadlineListFragment(), R.id.headline_list_fragment)
        }

        adjustFragmentsForOrientation()
    }

    // Method to show the content fragment based on orientation
    fun showContentFragment(content: String) {
        selectedContent = content

        if (isLandscape()) {
            // In landscape, load HeadlineFragment on the left and ContentFragment on the right
            loadFragment(HeadlineListFragment(), R.id.headline_list_fragment)
            loadFragment(NewsContentFragment.newInstance(content), R.id.news_content_fragment)
        } else {
            // In portrait, replace the main container with ContentFragment
            loadFragment(NewsContentFragment.newInstance(content), R.id.headline_list_fragment, true)
        }
    }

    // Handle orientation changes dynamically
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        adjustFragmentsForOrientation()
    }

    private fun adjustFragmentsForOrientation() {
        if (isLandscape()) {
            // In landscape mode, display HeadlineFragment on the left and ContentFragment (if available) on the right
            loadFragment(HeadlineListFragment(), R.id.headline_list_fragment)

            if (selectedContent != null) {
                findViewById<View>(R.id.news_content_fragment).visibility = View.VISIBLE
                loadFragment(NewsContentFragment.newInstance(selectedContent!!), R.id.news_content_fragment)
            } else {
                findViewById<View>(R.id.news_content_fragment).visibility = View.GONE
            }
        } else {
            findViewById<View>(R.id.news_content_fragment).visibility = View.GONE
        }
    }

    // Save the selected headline and content on configuration changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("selectedHeadline", selectedHeadline)
        outState.putString("selectedContent", selectedContent)
    }

    // Utility methods
    private fun loadFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    private fun isLandscape(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}


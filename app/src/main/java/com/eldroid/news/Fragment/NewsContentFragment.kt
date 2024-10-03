package com.eldroid.news.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eldroid.news.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsContentFragment : Fragment(R.layout.fragment_news_content) {

    companion object {
        private const val ARG_CONTENT = "content"

        // Factory method to create a new instance of this fragment with content
        fun newInstance(content: String): NewsContentFragment {
            val fragment = NewsContentFragment()
            val args = Bundle()
            args.putString(ARG_CONTENT, content)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the content passed from the previous fragment
        val content = arguments?.getString(ARG_CONTENT)

        // Display the content in a TextView
        view.findViewById<TextView>(R.id.content_text).text = content
    }
}
package com.eldroid.news.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldroid.news.R
import com.eldroid.news.Fragment.placeholder.PlaceholderContent
import com.eldroid.news.MainActivity
import com.eldroid.news.data.Headline

/**
 * A fragment representing a list of Items.
 */
class HeadlineListFragment : Fragment(R.layout.fragment_headline_list) {

    private lateinit var news: List<Headline>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Return a list of Headline objects
        news = listOf(
            Headline(getString(R.string.headline1), getString(R.string.news1)),
            Headline(getString(R.string.headline2), getString(R.string.news2)),
            Headline(getString(R.string.headline3), getString(R.string.news3)),
            Headline(getString(R.string.headline1), getString(R.string.news1)),
            Headline(getString(R.string.headline2), getString(R.string.news2))

        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HeadlineRecyclerViewAdapter(news) { position ->
            val content = news[position].content
            (activity as? MainActivity)?.showContentFragment(content)
        }
    }
}


package com.eldroid.news.Fragment

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eldroid.news.R

import com.eldroid.news.Fragment.placeholder.PlaceholderContent.PlaceholderItem
import com.eldroid.news.data.Headline
import com.eldroid.news.databinding.FragmentHeadlineListBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class HeadlineRecyclerViewAdapter(
    private val headlines: List<Headline>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<HeadlineRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = headlines[position]
        holder.titleView.text = item.title

        // Set background color based on even or odd position
        val backgroundColor = if (position % 2 == 0) {
            // Even position: #50C8A3
            Color.parseColor("#50C7A9")
        } else {
            // Odd position: #5CBEED
            Color.parseColor("#59BEF6")
        }
        holder.itemView.setBackgroundColor(backgroundColor)

        // Set the click listener to respond to item clicks
        holder.itemView.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int = headlines.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
    }
}

package com.vmlt.cinema.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.R
import com.vmlt.cinema.domain.entities.MovieBasicInfo

class MovieBasicInfoAdapter(val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<MovieBasicInfoAdapter.ViewHolder>() {
    private val mList: ArrayList<MovieBasicInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateList(movieList: List<MovieBasicInfo>) {
        mList.addAll(movieList)
        notifyDataSetChanged() // Just a warning, if we want to update the whole list we can use this function
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieIconImageView: ImageView = itemView.findViewById(R.id.movie_icon_image)
        private val movieNameTextView: TextView = itemView.findViewById(R.id.movie_name_text)

        fun bind(movie: MovieBasicInfo) {
            println("movie.iconId="+movie.iconId)
            movieIconImageView.setImageResource(movie.iconId)
            movieNameTextView.text = movie.name

            itemView.setOnClickListener {
                onItemClick(movie.id)
            }
        }
    }
}
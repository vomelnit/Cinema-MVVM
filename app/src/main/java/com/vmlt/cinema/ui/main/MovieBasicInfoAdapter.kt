package com.vmlt.cinema.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.R
import com.vmlt.cinema.utils.model.MovieBasicInfo
import com.vmlt.cinema.ui.details.MovieDetailsActivity


class MovieBasicInfoAdapter(private val mList: List<MovieBasicInfo>) :
    RecyclerView.Adapter<MovieBasicInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieInfo = mList[position]

        holder.movieIconImageView.setImageResource(movieInfo.iconId)
        holder.movieNameTextView.text = movieInfo.name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("MovieId", movieInfo.id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val movieIconImageView: ImageView = itemView.findViewById(R.id.movie_icon_image)
        val movieNameTextView: TextView = itemView.findViewById(R.id.movie_name_text)
    }
}
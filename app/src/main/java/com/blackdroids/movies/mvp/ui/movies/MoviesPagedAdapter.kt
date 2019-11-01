package com.blackdroids.movies.mvp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blackdroids.movies.mvp.R
import com.blackdroids.movies.mvp.data.api.ServerGateway
import com.blackdroids.movies.mvp.data.api.models.Movie
import com.blackdroids.movies.mvp.utils.ImageLoadUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesPagedAdapter(val onMovieClickListener: OnMovieClickListener) : PagedListAdapter<Movie, MoviesViewHolder>(
    DiffUtilCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MoviesViewHolder(
            inflater.inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            onMovieClickListener.onMovieClick(getItem(position)!!)
        }
    }

}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie){
        itemView.textViewTitle.text = movie.title
        itemView.textViewDescription.text = movie.overview
        ImageLoadUtils.loadImage(movie.posterPath!!, itemView.imageViewCover)
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}

interface OnMovieClickListener {
    fun onMovieClick(movie: Movie)
}
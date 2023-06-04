package com.example.moviesretrofitinternet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesretrofitinternet.R
import com.example.moviesretrofitinternet.databinding.ItemMovieBinding
import com.example.moviesretrofitinternet.model.Movie

class MovieAdapter(listener: OnManageMovieListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies: List<Movie>
    var listener: OnManageMovieListener = listener

    interface OnManageMovieListener {
        fun onShowTitleMovie(movie: Movie)
        fun onNavigateMovie(movie: Movie)
    }

    init {
        this.movies = emptyList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies.get(position), listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieBinding.bind(itemView)

        fun bind(movie: Movie, listener: OnManageMovieListener) {
            binding.tvTitle.text = movie.title
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(binding.imgImage)

            itemView.setOnClickListener(View.OnClickListener { listener.onShowTitleMovie(movie) })
            itemView.setOnLongClickListener(View.OnLongClickListener {
                listener.onNavigateMovie(movie)
                true
            })
        }
    }
}
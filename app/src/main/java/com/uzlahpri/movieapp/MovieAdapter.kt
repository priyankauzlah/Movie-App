package com.uzlahpri.movieapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uzlahpri.movieapp.activity.DetailActivity
import com.uzlahpri.movieapp.model.ResultsItem
import kotlinx.android.synthetic.main.movie_item.view.*
import org.jetbrains.anko.intentFor

class MovieAdapter(var context: Context, var listMovie: List<ResultsItem>?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: ResultsItem) {
            with(itemView) {
                tv_item_movie_title.text = movie.title
                tv_item_movie_synopsis.text = movie.overview
                tv_item_movie_release.text = movie.releaseDate.toString()
                tv_item_movie_genre.text = movie.genreIds.toString()
                tv_item_movie_rate.text = movie.voteAverage.toString()

                Glide.with(context).load(movie.posterPath).centerCrop().into(iv_item_movie_poster)
                itemView.setOnClickListener {
                    itemView.context.startActivity(
                        itemView.context.intentFor<DetailActivity>(
                            "EXTRA_MOVIE" to movie
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(listMovie?.get(position)!!)
    }

    override fun getItemCount(): Int = listMovie!!.size
}


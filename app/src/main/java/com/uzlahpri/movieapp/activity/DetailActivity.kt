package com.uzlahpri.movieapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.uzlahpri.movieapp.R
import com.uzlahpri.movieapp.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        fun getLaunchService(from: Context) = Intent(from, DetailActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        ib_detail_back.setOnClickListener { startActivity(Intent(MainActivity.getLaunchService(this))) }

        val movies = intent.getParcelableExtra<ResultsItem>("EXTRA_NEWS") as ResultsItem
        Glide.with(this).load(movies.backdropPath).into(iv_detail_movie_backdrop)
        Glide.with(this).load(movies.posterPath).into(iv_detail_movie_poster)
        tv_detail_movie_title.text = movies.title.toString()
        tv_detail_movie_genre.text = movies.genreIds.toString()
        tv_detail_movie_release.text = movies.releaseDate.toString()
        tv_detail_movie_rate.text = movies.voteAverage.toString()
        tv_detail_movie_synopsis_inside.text = movies.overview.toString()
    }

}
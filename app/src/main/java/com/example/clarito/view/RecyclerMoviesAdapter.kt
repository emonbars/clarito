package com.example.clarito.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clarito.Movie
import com.example.clarito.R
import com.squareup.picasso.Picasso

class RecyclerMoviesAdapter(var movies: ArrayList<Movie>?, var resource: Int) : RecyclerView.Adapter<RecyclerMoviesAdapter.CardMovieHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardMovieHolder {
        val view: View = LayoutInflater.from(p0!!.context).inflate(resource, p0, false)
        return CardMovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardMovieHolder, p1: Int) {
        val movie = movies?.get(p1)
        if (movie != null) {
            p0.setDataCard(movie)
        }
    }

    class CardMovieHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var movie: Movie? = null
        private var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        private var txVoteAverage: TextView = v.findViewById(R.id.txVoteAverage)
        private var tvDate: TextView = v.findViewById(R.id.tvDate)
        private var imgCoupon: ImageView = v.findViewById(R.id.imgCoupon)
        init {
            v.setOnClickListener(this)
        }

        fun setDataCard(movies: Movie){
            this.movie = movies
            tvTitle.setText(movies.title)
            tvDate.setText(movies.release_date)
            txVoteAverage.setText(movies.vote_average)
            var linkImg = "https://www.themoviedb.org/t/p/w220_and_h330_face/" + movies.poster_path
            Picasso.get().load(linkImg).resize(720, 1000).centerCrop().into(imgCoupon)
        }

        override fun onClick(v: View) {
            val context = v.context
            val showPhotoIntent = Intent(context, MovieDetailActivity::class.java)
            showPhotoIntent.putExtra("MOVIE", movie)
            context.startActivity(showPhotoIntent)

        }

    }

}
package com.example.clarito.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clarito.Movie
import com.example.clarito.R
import com.example.clarito.Video
import com.example.clarito.model.ApiMovies
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailActivity : AppCompatActivity() {

    private var movieSelected: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieSelected = intent.getSerializableExtra("MOVIE") as Movie

        var tvTitleDetail: TextView = findViewById(R.id.tvHeadTitle)
        var tvOverview: TextView = findViewById(R.id.tvOverview)
        var tvoriginalLanguage: TextView = findViewById(R.id.tvoriginalLanguage)
        var imgHeaderDetail: ImageView = findViewById(R.id.imgHeaderDetail)
        var tvReleaseDate: TextView = findViewById(R.id.tvReleaseDate)
        var tvvoteAverage: TextView = findViewById(R.id.tvvoteAverage)

        tvTitleDetail.text = movieSelected?.title
        tvOverview.text = movieSelected?.overview
        tvReleaseDate.text = movieSelected?.release_date
        tvoriginalLanguage.text = movieSelected?.original_language
        tvvoteAverage.text = movieSelected?.vote_average

        var linkImg = "https://www.themoviedb.org/t/p/w220_and_h330_face/" + movieSelected?.poster_path
        Picasso.get().load(linkImg).resize(720, 1000).centerCrop().into(imgHeaderDetail)

        val rvTrailers: RecyclerView = findViewById(R.id.rvTrailers)
        rvTrailers.layoutManager = LinearLayoutManager(this)
        val videos = ArrayList<Video>()

        val apiMovie = ApiMovies()
        val apiService = apiMovie.getClientService()
        val call = apiService.getMoviesVideos(movieSelected?.id)

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("results")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var video = Video(jsonObject)
                    videos.add(video)
                }

                // access to card videos
                rvTrailers.adapter = RecyclerVideosAdapter(videos, R.layout.trailer_movie)
            }
        })
    }
}

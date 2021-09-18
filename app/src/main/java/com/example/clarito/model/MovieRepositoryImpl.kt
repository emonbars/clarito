package com.example.clarito.model

import android.util.Log
import com.example.clarito.Movie
import com.example.clarito.presenter.MoviePresenter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(var moviePresenter: MoviePresenter): MovieRepository {
    override fun getMoviesAPI(position: Int) {
        val movies: ArrayList<Movie>? = ArrayList<Movie>()
        val apiMovie = ApiMovies()
        val apiService = apiMovie.getClientService()
        val call: Call<JsonObject> = if (position == 0) {
            apiService.getMovies()
        } else {
           apiService.getNowPlaying()
        }
        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("results")

                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val movie = Movie(jsonObject)
                    movies?.add(movie)
                }
                // access to card movie
                // rvMovies?.adapter = RecyclerMoviesAdapter(movies, R.layout.card_movie)
                moviePresenter.showMovies(movies)
            }
        })
    }
}
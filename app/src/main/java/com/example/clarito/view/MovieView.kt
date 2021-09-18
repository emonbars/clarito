package com.example.clarito.view

import com.example.clarito.Movie

interface MovieView {
    // view
    fun showMovies (videos: ArrayList<Movie>?)

    // presenter
    fun getMovies(position: Int)
}
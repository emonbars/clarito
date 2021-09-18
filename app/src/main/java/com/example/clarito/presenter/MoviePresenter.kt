package com.example.clarito.presenter

import com.example.clarito.Movie

interface MoviePresenter {
    // view
    fun showMovies (videos: ArrayList<Movie>?)

    // interact
    fun getMovies(position: Int)
}
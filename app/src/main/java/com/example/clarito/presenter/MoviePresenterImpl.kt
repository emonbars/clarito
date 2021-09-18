package com.example.clarito.presenter

import com.example.clarito.Movie
import com.example.clarito.model.MoviesInteractor
import com.example.clarito.model.MoviesInteractorImpl
import com.example.clarito.view.MovieView

class MoviePresenterImpl(var movieView: MovieView ): MoviePresenter {

    private var movieInteractor: MoviesInteractor = MoviesInteractorImpl(this)

    override fun showMovies(videos: ArrayList<Movie>?) {
        movieView.showMovies(videos)
    }

    override fun getMovies(position: Int) {
        movieInteractor.getMoviesAPI(position)
    }

}
package com.example.clarito.model

import com.example.clarito.presenter.MoviePresenter

class MoviesInteractorImpl(var moviePresenter: MoviePresenter): MoviesInteractor {

    private var movieRepository: MovieRepository = MovieRepositoryImpl(moviePresenter)

    override fun getMoviesAPI(position: Int) {
        movieRepository.getMoviesAPI(position)
    }
}
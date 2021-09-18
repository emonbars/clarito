package com.example.clarito.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clarito.Movie
import com.example.clarito.R
import com.example.clarito.presenter.MoviePresenter
import com.example.clarito.presenter.MoviePresenterImpl
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), MovieView {

    private var moviePresenter: MoviePresenter? = null
    private var rvMovies: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        moviePresenter = MoviePresenterImpl(this)

        val tabLayout: TabLayout = findViewById(R.id.tabHome)
        rvMovies = findViewById(R.id.rvMovies)
        rvMovies?.layoutManager = LinearLayoutManager(this)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
             override fun onTabSelected(tab: TabLayout.Tab) {
                 getMovies(tab.getPosition())
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("this is unselected tab.position $tab ")
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        getMovies(0)
    }

    override fun showMovies(videos: ArrayList<Movie>?) {
        try {
            rvMovies!!.adapter = RecyclerMoviesAdapter(videos, R.layout.card_movie)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun getMovies(position: Int) {
        moviePresenter?.getMovies(position)
    }
}



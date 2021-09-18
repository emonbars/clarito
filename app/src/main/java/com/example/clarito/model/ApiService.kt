package com.example.clarito.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular/")
    fun getMovies(): Call<JsonObject>

    @GET("movie/upcoming/")
    fun getNowPlaying(): Call<JsonObject>

    @GET("movie/{id}")
    open fun getMoviesDetail(@Path("id") id: Int?): Call<JsonObject>

    @GET("movie/{id}/videos")
    open fun getMoviesVideos(@Path("id") id: Int?): Call<JsonObject>
}
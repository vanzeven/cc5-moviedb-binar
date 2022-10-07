package com.example.moviedb.service

import com.example.moviedb.model.GetPopularItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=6cf7c98d8b65a3bc61f70dd2f72accf2")
    fun getPopular(): Call<List<GetPopularItem>>
}
package com.example.moviedb.service

import com.example.moviedb.model.GetPopularItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    fun getPopular(): Call<List<GetPopularItem>>
}
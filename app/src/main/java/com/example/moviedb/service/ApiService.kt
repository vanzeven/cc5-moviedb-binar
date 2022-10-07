package com.example.moviedb.service

import com.example.moviedb.model.GetPopularItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") api_key: String = "6cf7c98d8b65a3bc61f70dd2f72accf2"
    ): Call<List<GetPopularItem>>
}
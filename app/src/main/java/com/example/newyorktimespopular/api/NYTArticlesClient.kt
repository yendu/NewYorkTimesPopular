package com.example.newyorktimespopular.api


import com.example.newyorktimespopular.model.Articles
import retrofit2.http.GET
import retrofit2.http.Path


interface NYTArticlesClient {

    @GET("mostpopular/v2/viewed/{day}.json?api-key=MtRTvmW2V60UGhJk7WcdcPlDBnS78NVG")
    suspend fun getArticles(@Path("day") id: String?): Articles?
}
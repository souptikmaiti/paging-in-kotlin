package com.example.pagingwithkotlin.data.network

import com.example.pagingwithkotlin.data.models.GitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoService {

    @GET("search/repositories?sort=stars")
    fun getGitRepos(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int,
        @Query("q") topic:String
    ):Call<GitResponse>
}
package com.example.pagingwithkotlin.pagingdata

import androidx.paging.PageKeyedDataSource
import com.example.pagingwithkotlin.data.models.GitItem
import com.example.pagingwithkotlin.data.models.GitResponse
import com.example.pagingwithkotlin.data.network.GitRepoService
import com.example.pagingwithkotlin.data.network.GitRepoServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitDataSource:PageKeyedDataSource<Int,GitItem>() {
    companion object{
        const val FIRST_PAGE = 1
        const val PER_PAGE = 10
        const val TOPIC = "android"
    }
    private val gitService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitItem>) {
        val call = gitService.getGitRepos(FIRST_PAGE, PER_PAGE, TOPIC)
        call.enqueue(object: Callback<GitResponse>{
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    val gitItems = apiResponse?.items
                    gitItems.let {
                        callback.onResult(gitItems!!,null,FIRST_PAGE +1)
                    }
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitItem>) {
        val call = gitService.getGitRepos(params.key, PER_PAGE, TOPIC)
        call.enqueue(object: Callback<GitResponse>{
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    val gitItems = apiResponse?.items
                    val key = if(params.key < apiResponse!!.totalCount) params.key+1 else apiResponse.totalCount
                    gitItems.let {
                        callback.onResult(gitItems!!,key)
                    }
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitItem>) {
        val call = gitService.getGitRepos(params.key, PER_PAGE, TOPIC)
        call.enqueue(object: Callback<GitResponse>{
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()
                    val gitItems = apiResponse?.items
                    val key = if(params.key > 1) params.key-1 else 1
                    gitItems.let {
                        callback.onResult(gitItems!!,key)
                    }
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {

            }
        })
    }
}
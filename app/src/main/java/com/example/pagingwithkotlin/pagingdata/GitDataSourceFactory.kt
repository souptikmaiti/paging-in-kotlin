package com.example.pagingwithkotlin.pagingdata

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingwithkotlin.data.models.GitItem

class GitDataSourceFactory: DataSource.Factory<Int, GitItem>() {
    val gitLiveDataSource = MutableLiveData<GitDataSource>()
    override fun create(): DataSource<Int, GitItem> {
        val gitDataSource = GitDataSource()
        gitLiveDataSource.postValue(gitDataSource)
        return gitDataSource
    }
}
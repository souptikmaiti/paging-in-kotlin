package com.example.pagingwithkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingwithkotlin.data.models.GitItem
import com.example.pagingwithkotlin.pagingdata.GitDataSource
import com.example.pagingwithkotlin.pagingdata.GitDataSourceFactory

class GitViewModel: ViewModel() {
    var gitPagedList : LiveData<PagedList<GitItem>>
    private val liveDataSource: LiveData<GitDataSource>

    init {
        val gitDataSourceFactory = GitDataSourceFactory()
        liveDataSource = gitDataSourceFactory.gitLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(GitDataSource.PER_PAGE)
            .build()

        gitPagedList = LivePagedListBuilder(gitDataSourceFactory,config).build()
    }

}
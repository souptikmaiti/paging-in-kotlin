package com.example.pagingwithkotlin.data.models


import com.google.gson.annotations.SerializedName

data class GitResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<GitItem>,
    @SerializedName("total_count")
    val totalCount: Int
)
package com.example.pagingwithkotlin.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitRepoServiceBuilder {
    private const val BASE_URL = "https://api.github.com/"
    private var logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val okHttp = OkHttpClient.Builder().addInterceptor(logger).build()

    private val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttp)

    private val retrofit = retrofitBuilder.build()

    fun <T> buildService(serviceType: Class<T>) :T {
        return retrofit.create(serviceType)
    }
}
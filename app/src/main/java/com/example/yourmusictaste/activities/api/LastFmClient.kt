package com.example.yourmusictaste.activities.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object LastFmClient {
    private const val apiKey = "b4d31c64987f353b709f48d6644054cd"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalUrl = chain.request().url()

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val request = chain.request().newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(request)
        }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://ws.audioscrobbler.com")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: LastFmRequests = retrofit.create(LastFmRequests::class.java)

}


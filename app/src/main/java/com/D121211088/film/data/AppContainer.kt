package com.D121211088.news.data

import com.D121211088.film.data.repository.MoviesRepository
import com.D121211088.film.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppCointainer {
    val moviesRepository: MoviesRepository
}

class DefaultAppContainer: AppCointainer {

    private val BASE_URL = "https://api.themoviedb.org"

    // authorization token
    private val AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NGM1NjgxYWE3MTdjODlmZTdmMmE1ZTcyYTNhNWZmZCIsInN1YiI6IjYyZTRhOTJiZmM1ZjA2MDA1OWMyOGUwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ"

    // interceptor to add the "Authorization" header
//    val headerInterceptor = Interceptor { chain ->
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", AUTH_TOKEN)
//            .build()
//        chain.proceed(request)
//    }

    // OkHttpClient with the interceptor
//    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(headerInterceptor)
//        .build()

    private val retrofit = Retrofit.Builder()
//        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val moviesRepository: MoviesRepository
        get() = MoviesRepository(retrofitService)
}
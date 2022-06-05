package com.example.f1calendarOP

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://ergast.com"

    val api : RaceApi by lazy{

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RaceApi::class.java)
    }
}
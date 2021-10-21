package com.uogames.i_have_quest.networking.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.uogames.i_have_quest.networking.GameService
import com.uogames.networking.cllient.HttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object GameApi {


    fun provideRetrofit(): GameService {
        val retrofit = Retrofit.Builder()
            //.baseUrl("http://93.125.42.151:8080/IHaveQuest/")
            .baseUrl("https://192.168.1.2:8080/IHaveQuest/")
            .client(HttpClient.client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()

        return retrofit.create()
    }

}
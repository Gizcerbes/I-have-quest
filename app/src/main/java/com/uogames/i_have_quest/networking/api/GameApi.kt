package com.uogames.i_have_quest.networking.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.uogames.i_have_quest.networking.GameService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object GameApi {

    fun provideRetrofit(): GameService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.169.0.101:8080/IHaveQuest/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()

        return retrofit.create()
    }

}
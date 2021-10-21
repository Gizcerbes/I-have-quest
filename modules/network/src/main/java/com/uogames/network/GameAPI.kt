package com.uogames.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

public object GameAPI {
	fun provideRetrofit(): GameService {
		val client = HttpClient.client
		val retrofit = Retrofit.Builder()
			.baseUrl("https://93.125.42.151:8080")
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
			.client(client)
			.build()
		return retrofit.create()
	}
}
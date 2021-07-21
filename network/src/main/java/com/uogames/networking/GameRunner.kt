package com.uogames.networking

import com.uogames.data.mappers.Mapper
import retrofit2.Response

object GameRunner {

    suspend fun <R, E> run(response: Response<R>, mapper: Mapper<R, E>) : E? {
        if (response.isSuccessful) {
            return response.body()?.let { mapper.map(it) }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

}
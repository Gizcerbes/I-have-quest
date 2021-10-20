package com.uogames.network.data.mappers

public interface Mapper<S, N> {

	fun S.toDTO(): N

}
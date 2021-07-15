package com.uogames.i_have_quest.data.mappers

interface Mapper<F, T> {

    fun map(from: F): T

}
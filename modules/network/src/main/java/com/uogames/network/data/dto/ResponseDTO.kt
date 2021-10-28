package com.uogames.network.data.dto

data class ResponseDTO<T>(val any: T?, val status: Status)
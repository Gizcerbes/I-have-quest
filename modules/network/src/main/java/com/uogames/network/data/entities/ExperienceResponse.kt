package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class ExperienceResponse(
    @SerializedName("experience")
    val experience: Long?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("lvl")
    val lvl: Long?
)
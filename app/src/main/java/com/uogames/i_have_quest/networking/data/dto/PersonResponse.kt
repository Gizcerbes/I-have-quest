package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("experience")
    val experience: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: Int?,
    @SerializedName("lvl")
    val lvl: Int?,
    @SerializedName("personName")
    val personName: String?,
    @SerializedName("title")
    val title: String?
)
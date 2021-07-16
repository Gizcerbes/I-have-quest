package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class PersonObjectResponse(
    @SerializedName("experience")
    val experience: Long?,
    @SerializedName("nextLvl")
    val nextLvl: Long?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("image")
    val image: Long?,
    @SerializedName("lvl")
    val lvl: Long?,
    @SerializedName("personName")
    val personName: String?,
    @SerializedName("title")
    val title: String?
)
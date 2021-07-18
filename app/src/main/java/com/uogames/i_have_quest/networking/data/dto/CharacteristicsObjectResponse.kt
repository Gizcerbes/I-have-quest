package com.uogames.i_have_quest.networking.data.dto

import com.google.gson.annotations.SerializedName

data class CharacteristicsObjectResponse (
    @SerializedName("agility")
    val agility: Int?,
    @SerializedName("defence")
    val defence: Int?,
    @SerializedName("force")
    val force: Int?,
    @SerializedName("health")
    val health: Int?,
    @SerializedName("id")
    val id: Long?
)
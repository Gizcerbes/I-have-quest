package com.uogames.network.data.entities

import com.google.gson.annotations.SerializedName

data class CharacteristicResponse(
    @SerializedName("agility")
    val agility: Int?,
    @SerializedName("defence")
    val defence: Int?,
    @SerializedName("force")
    val force: Int?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("vitality")
    val vitality: Int?
)

package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class CharacteristicResponse(
    @SerializedName("characteristic")
    val characteristic: Characteristic?,
    @SerializedName("status")
    val status: Status?
) {
    data class Characteristic(
        @SerializedName("agility")
        val agility: Int?,
        @SerializedName("defence")
        val defence: Int?,
        @SerializedName("force")
        val force: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("vitality")
        val vitality: Int?
    )

    data class Status(
        @SerializedName("message")
        val message: String?,
        @SerializedName("timestamp")
        val timestamp: Long?,
        @SerializedName("type")
        val type: Type?
    ) {
        data class Type(
            @SerializedName("description")
            val description: String?,
            @SerializedName("value")
            val value: Int?
        )
    }
}
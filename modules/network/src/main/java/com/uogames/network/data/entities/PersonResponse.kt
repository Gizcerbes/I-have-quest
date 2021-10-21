package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("person")
    val person: Person?,
    @SerializedName("status")
    val status: Status?
) {
    data class Person(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("title")
        val title: String?
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
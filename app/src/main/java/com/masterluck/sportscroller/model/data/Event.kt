package com.masterluck.sportscroller.model.data

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("i")
    val id: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("d")
    val name: String,
    @SerializedName("tt")
    val time: Long,
    var isFavorite: Boolean = false
)

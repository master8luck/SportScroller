package com.masterluck.sportscroller.model.data

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("i")
    private val id: String,
    @SerializedName("si")
    private val sportId: String,
    @SerializedName("d")
    private val name: String,
    @SerializedName("tt")
    private val time: Long,
)

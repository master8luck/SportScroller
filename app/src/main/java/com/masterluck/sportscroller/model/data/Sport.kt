package com.masterluck.sportscroller.model.data

import com.google.gson.annotations.SerializedName

data class Sport(
    @SerializedName("i")
    private val id: String,
    @SerializedName("d")
    private val name: String,
    @SerializedName("e")
    private val events: List<Event>,
)

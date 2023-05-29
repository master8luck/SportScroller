package com.masterluck.sportscroller.model.data

import com.google.gson.annotations.SerializedName

data class Sport(
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val name: String,
    @SerializedName("e")
    val events: MutableList<Event>,
    var isCollapsed: Boolean = false
)

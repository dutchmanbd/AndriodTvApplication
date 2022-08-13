package com.ticonsys.andriodtvapplication

import com.google.gson.annotations.SerializedName

class VideoCard(
    @SerializedName("sources")
    val sources: List<String> = emptyList(),
    @SerializedName("background")
    val background: String = "",
    @SerializedName("studio")
    val studio: String = ""
) : Card()
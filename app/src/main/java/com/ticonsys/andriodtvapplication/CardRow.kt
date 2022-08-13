package com.ticonsys.andriodtvapplication

import com.google.gson.annotations.SerializedName

data class CardRow(
    @SerializedName("type")
    var mType: Int = TYPE_DEFAULT,
    @SerializedName("shadow")
    val mShadow: Boolean = true,
    @SerializedName("title")
    val mTitle: String? = null,
    @SerializedName("cards")
    val mCards: List<Card> = emptyList()
) {


    companion object {
        // default is a list of cards
        const val TYPE_DEFAULT = 0

        // section header
        const val TYPE_SECTION_HEADER = 1

        // divider
        const val TYPE_DIVIDER = 2
    }


}
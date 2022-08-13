package com.ticonsys.andriodtvapplication

import com.google.gson.annotations.SerializedName

open class Card {
    @SerializedName("title")
    var title: String? = ""

    @SerializedName("description")
    val description: String = ""

    @SerializedName("extraText")
    val extraText: String = ""

    @SerializedName("card")
    val imageUrl: String? = null

    @SerializedName("footerColor")
    val footerColor: String? = null

    @SerializedName("selectedColor")
    val selectedColor: String? = null

    @SerializedName("localImageResource")
    val localImageResource: String? = null

    @SerializedName("footerIconLocalImageResource")
    val footerResource: String? = null

    @SerializedName("type")
    val type: Card.Type? = null

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("width")
    val width: Int = 0

    @SerializedName("height")
    val height: Int = 0

    enum class Type {
        MOVIE_COMPLETE, MOVIE, MOVIE_BASE, ICON, SQUARE_BIG, SINGLE_LINE, GAME, SQUARE_SMALL, DEFAULT, SIDE_INFO, SIDE_INFO_TEST_1, TEXT, CHARACTER, GRID_SQUARE, VIDEO_GRID
    }
}
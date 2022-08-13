package com.ticonsys.andriodtvapplication.presenters

import android.content.Context
import androidx.leanback.widget.ImageCardView
import com.bumptech.glide.Glide
import com.ticonsys.andriodtvapplication.Card
import com.ticonsys.andriodtvapplication.R
import com.ticonsys.andriodtvapplication.VideoCard

class VideoCardViewPresenter(
    context: Context,
    cardThemeResId: Int = R.style.DefaultCardTheme
) : ImageCardViewPresenter(context, cardThemeResId) {


    override fun onBindViewHolder(card: Card?, cardView: ImageCardView) {
        super.onBindViewHolder(card, cardView)
        val videoCard = card as VideoCard?
        videoCard ?: return
        Glide.with(context)
            .asBitmap()
            .load(videoCard.imageUrl)
            .into(cardView.mainImageView)
    }
}
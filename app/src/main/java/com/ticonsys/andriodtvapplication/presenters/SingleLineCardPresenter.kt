package com.ticonsys.andriodtvapplication.presenters

import android.content.Context
import android.graphics.Color
import androidx.leanback.widget.ImageCardView
import com.ticonsys.andriodtvapplication.Card
import com.ticonsys.andriodtvapplication.R

class SingleLineCardPresenter(
    context: Context
) : ImageCardViewPresenter(context, R.style.SingleLineCardTheme) {

    override fun onBindViewHolder(card: Card?, cardView: ImageCardView) {
        super.onBindViewHolder(card, cardView)
        val footerColor = if(card?.footerColor != null) Color.parseColor(card.footerColor) else -1
        cardView.setInfoAreaBackgroundColor(footerColor)
    }
}
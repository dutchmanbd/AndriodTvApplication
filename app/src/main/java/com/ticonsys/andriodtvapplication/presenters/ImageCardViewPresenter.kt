package com.ticonsys.andriodtvapplication.presenters

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.leanback.widget.ImageCardView
import com.bumptech.glide.Glide
import com.ticonsys.andriodtvapplication.Card
import com.ticonsys.andriodtvapplication.R

open class ImageCardViewPresenter(
    context: Context,
    cardThemeResId: Int = R.style.DefaultCardTheme
) : AbstractCardPresenter<ImageCardView>(
    ContextThemeWrapper(context, cardThemeResId)
) {
    override fun onCreateView(): ImageCardView {
        return ImageCardView(context)
    }

    override fun onBindViewHolder(card: Card?, cardView: ImageCardView) {
        card ?: return
        cardView.tag = card
        cardView.titleText = card.title
        cardView.contentText = card.description
        if (card.localImageResource != null) {
            val resourceId = context.resources.getIdentifier(
                card.localImageResource, "drawable", context.packageName
            )
            Glide.with(context)
                .asBitmap()
                .load(resourceId)
                .into(cardView.mainImageView)
        }
    }
}
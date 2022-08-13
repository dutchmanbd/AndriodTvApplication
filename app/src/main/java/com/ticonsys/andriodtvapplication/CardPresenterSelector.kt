package com.ticonsys.andriodtvapplication

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.ticonsys.andriodtvapplication.presenters.ImageCardViewPresenter
import com.ticonsys.andriodtvapplication.presenters.SingleLineCardPresenter
import com.ticonsys.andriodtvapplication.presenters.VideoCardViewPresenter

class CardPresenterSelector(
    context: Context
) : PresenterSelector(){

    private val mContext: Context = context

    private val presenters = HashMap<Card.Type, Presenter>()


    override fun getPresenter(item: Any?): Presenter {
        if (item !is Card) throw RuntimeException(
            String.format(
                "The PresenterSelector only supports data items of type '%s'",
                Card::class.java.name
            )
        )
        val card = item
        var presenter = presenters[card.type]
        if (presenter == null) {
            when (card.type) {
                Card.Type.SINGLE_LINE -> presenter = SingleLineCardPresenter(mContext)
                Card.Type.VIDEO_GRID -> presenter =
                    VideoCardViewPresenter(mContext, R.style.VideoGridCardTheme)
                Card.Type.MOVIE, Card.Type.MOVIE_BASE, Card.Type.MOVIE_COMPLETE, Card.Type.SQUARE_BIG, Card.Type.GRID_SQUARE, Card.Type.GAME -> {
                    var themeResId: Int = R.style.MovieCardSimpleTheme
                    if (card.type === Card.Type.MOVIE_BASE) {
                        themeResId = R.style.MovieCardBasicTheme
                    } else if (card.type === Card.Type.MOVIE_COMPLETE) {
                        themeResId = R.style.MovieCardCompleteTheme
                    } else if (card.type === Card.Type.SQUARE_BIG) {
                        themeResId = R.style.SquareBigCardTheme
                    } else if (card.type === Card.Type.GRID_SQUARE) {
                        themeResId = R.style.GridCardTheme
                    } else if (card.type === Card.Type.GAME) {
                        themeResId = R.style.GameCardTheme
                    }
                    presenter = ImageCardViewPresenter(mContext, themeResId)
                }
//                Card.Type.SIDE_INFO -> presenter = SideInfoCardPresenter(mContext)
//                Card.Type.TEXT -> presenter = TextCardPresenter(mContext)
//                Card.Type.ICON -> presenter = IconCardPresenter(mContext)
//                Card.Type.CHARACTER -> presenter = CharacterCardPresenter(mContext)
                else -> presenter = ImageCardViewPresenter(mContext)
            }
        }
        presenters[card.type!!] = presenter
        return presenter
    }
}
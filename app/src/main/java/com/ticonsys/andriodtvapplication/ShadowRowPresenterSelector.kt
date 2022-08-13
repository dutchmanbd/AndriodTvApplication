package com.ticonsys.andriodtvapplication

import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector

class ShadowRowPresenterSelector : PresenterSelector() {

    private val mShadowEnabledRowPresenter = ListRowPresenter().apply {
        setNumRows(1)
    }
    private val mShadowDisabledRowPresenter = ListRowPresenter().apply {
        shadowEnabled = false
    }


    override fun getPresenter(item: Any?): Presenter {
        if (item !is CardListRow) return mShadowDisabledRowPresenter
        val row: CardRow = item.cardRow
        return if (row.mShadow) mShadowEnabledRowPresenter else mShadowDisabledRowPresenter
    }

    override fun getPresenters(): Array<Presenter> {
        return arrayOf(mShadowDisabledRowPresenter, mShadowEnabledRowPresenter)
    }
}
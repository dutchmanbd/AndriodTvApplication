package com.ticonsys.andriodtvapplication.presenters

import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.Presenter
import com.ticonsys.andriodtvapplication.Card

@Suppress("UNCHECKED_CAST")
abstract class AbstractCardPresenter<T : BaseCardView>(
    val context: Context
) : Presenter() {

    companion object {
        private const val TAG = "AbstractCardPresenter"
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val cardView = onCreateView()
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val card = item as Card?
        try {
            val holder = viewHolder as T
            onBindViewHolder(card, holder)
        } catch (e: Exception){}
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        onUnbindViewHolder(viewHolder as T)
    }

    open fun onUnbindViewHolder(cardView: T) {

    }

    protected abstract fun onCreateView(): T

    abstract fun onBindViewHolder(card: Card?, cardView: T)

}
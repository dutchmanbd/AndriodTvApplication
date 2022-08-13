package com.ticonsys.andriodtvapplication

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ObjectAdapter

class CardListRow(
    header: HeaderItem,
    adapter: ObjectAdapter,
    val cardRow: CardRow
) : ListRow(header, adapter) {

}
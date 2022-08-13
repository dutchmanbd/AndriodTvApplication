package com.ticonsys.andriodtvapplication

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.*
import com.google.gson.Gson

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : BrowseSupportFragment() {

    private lateinit var mBackgroundManager: BackgroundManager
    private var mRowsAdapter: ArrayObjectAdapter? = null


    companion object {
        private const val TAG = "MainFragment"

        private const val HEADER_ID_1: Long = 1
        private const val HEADER_NAME_1 = "Page Fragment"
        private const val HEADER_ID_2: Long = 2
        private const val HEADER_NAME_2 = "Rows Fragment"
        private const val HEADER_ID_3: Long = 3
        private const val HEADER_NAME_3 = "Settings Fragment"
        private const val HEADER_ID_4: Long = 4
        private const val HEADER_NAME_4 = "User agreement Fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        loadData()
        mBackgroundManager = BackgroundManager.getInstance(requireActivity())
        mBackgroundManager.attach(requireActivity().window)
        mainFragmentRegistry.registerFragment(PageRow::class.java, PageRowFragmentFactory(mBackgroundManager))
    }

    private fun setupUi() {
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.fastlane_background)
        title = "Title goes here"
        setOnSearchClickedListener {
            Toast.makeText(
                activity, getString(R.string.implement_search), Toast.LENGTH_SHORT
            ).show()
        }

        prepareEntranceTransition()
    }

    private fun loadData() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        Handler().postDelayed({
            createRows()
            startEntranceTransition()
        }, 2000)
    }

    private fun createRows() {
        val headerItem1 = HeaderItem(
            HEADER_ID_1, HEADER_NAME_1
        )
        val pageRow1 = PageRow(headerItem1)
        mRowsAdapter?.add(pageRow1)
        val headerItem2 = HeaderItem(
            HEADER_ID_2,
            HEADER_NAME_2
        )
        val pageRow2 = PageRow(headerItem2)
        mRowsAdapter?.add(pageRow2)
        val headerItem3 = HeaderItem(
            HEADER_ID_3,
            HEADER_NAME_3
        )
        val pageRow3 = PageRow(headerItem3)
        mRowsAdapter?.add(pageRow3)
        val headerItem4 = HeaderItem(
            HEADER_ID_4,
            HEADER_NAME_4
        )
        val pageRow4 = PageRow(headerItem4)
        mRowsAdapter?.add(pageRow4)
    }

    class PageRowFragmentFactory(
        private val backgroundManager: BackgroundManager
    ) : BrowseSupportFragment.FragmentFactory<Fragment>() {
        override fun createFragment(row: Any): Fragment {
            backgroundManager.drawable = null
            if (row is Row) {
                when (row.headerItem.id) {
                    HEADER_ID_1 -> {
                        return SampleFragmentA()
                    }
                    HEADER_ID_2 -> {
                        return SampleFragmentB()
                    }
                    HEADER_ID_3 -> {
                        return SettingsFragment()
                    }
                    HEADER_ID_4 -> {
                        return WebViewFragment()
                    }
                }
            }
            throw IllegalArgumentException()
        }

    }

    class SampleFragmentA : VerticalGridSupportFragment(), MainFragmentAdapterProvider {
        private val COLUMNS = 4
        private val ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL
        private var mAdapter: ArrayObjectAdapter? = null

        private val mMainFragmentAdapter = SampleFragmentAAdapterImpl(this)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setupAdapter()
            loadData()
            mMainFragmentAdapter.fragmentHost.notifyViewCreated(mMainFragmentAdapter)
            mMainFragmentAdapter.fragmentHost.notifyDataReady(mMainFragmentAdapter)
        }

        private fun setupAdapter(){
            val presenter = VerticalGridPresenter(ZOOM_FACTOR)
            presenter.numberOfColumns = COLUMNS
            gridPresenter = presenter

            val cardPresenter = CardPresenterSelector(activity!!)
            mAdapter = ArrayObjectAdapter(cardPresenter)
            adapter = mAdapter
        }

        private fun loadData() {
            val json = Utils.inputStreamToString(
                resources.openRawResource(
                    R.raw.grid_example
                )
            )
            val cardRow = Gson().fromJson(json, CardRow::class.java)
            mAdapter!!.addAll(0, cardRow.mCards)
        }


        override fun getMainFragmentAdapter(): MainFragmentAdapter<*> {
            return mMainFragmentAdapter
        }


    }

    class SampleFragmentB : RowsSupportFragment() {
        private val mRowsAdapter = ArrayObjectAdapter(ShadowRowPresenterSelector())

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            adapter = mRowsAdapter
            createRows()
            if(mainFragmentAdapter != null){
                mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
            }
        }

        private fun createRows() {
            val json = Utils.inputStreamToString(
                resources.openRawResource(
                    R.raw.page_row_example
                )
            )
            val rows = Gson().fromJson(
                json,
                Array<CardRow>::class.java
            )
            for (row in rows) {
                if (row.mType == CardRow.TYPE_DEFAULT) {
                    mRowsAdapter.add(createCardRow(row))
                }
            }
        }

        private fun createCardRow(cardRow: CardRow): Row {
            val presenterSelector: PresenterSelector = CardPresenterSelector(requireContext())
            val adapter = ArrayObjectAdapter(presenterSelector)
            for (card in cardRow.mCards) {
                adapter.add(card)
            }
            val headerItem = HeaderItem(cardRow.mTitle)
            return CardListRow(headerItem, adapter, cardRow)
        }

    }

    class SettingsFragment : RowsSupportFragment() {

    }

    class WebViewFragment : Fragment() {

    }

    class SampleFragmentAAdapterImpl(mFragment: SampleFragmentA): MainFragmentAdapter<SampleFragmentA>(mFragment)


}
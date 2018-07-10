package com.example.cld.youtueclient.searchScreen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.SearchListItem
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.search_view.*
import java.util.concurrent.TimeUnit

class SearchFragment : MvpAppCompatFragment(), SearchView {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchRecycler.layoutManager = LinearLayoutManager(context)
        searchRecycler.adapter = RecyclerAdapter()

        searchEditText.textChanges()
                .debounce(400,TimeUnit.MILLISECONDS)
                .subscribe { presenter.search(it.toString())}
    }

    override fun updateList(items: MutableList<SearchListItem>) {
        (searchRecycler.adapter as RecyclerAdapter).items = items
        searchRecycler.adapter?.notifyDataSetChanged()
    }

}
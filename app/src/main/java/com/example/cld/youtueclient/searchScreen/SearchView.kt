package com.example.cld.youtueclient.searchScreen

import com.arellomobile.mvp.MvpView
import com.example.cld.youtueclient.dataLayer.SearchListItem

interface SearchView : MvpView {
    fun updateList(items: MutableList<SearchListItem>)
}
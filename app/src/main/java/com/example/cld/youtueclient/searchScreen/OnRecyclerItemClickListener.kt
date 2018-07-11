package com.example.cld.youtueclient.searchScreen

import com.example.cld.youtueclient.dataLayer.SearchListItem

interface OnRecyclerItemClickListener {
    fun onItemClick(item: SearchListItem)
}
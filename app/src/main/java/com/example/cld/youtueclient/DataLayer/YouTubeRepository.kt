package com.example.cld.youtueclient.DataLayer

import io.reactivex.Observable

interface YouTubeRepository {
    fun searchVideo(queryText: String) : Observable<Array<SearchListItem>>
}
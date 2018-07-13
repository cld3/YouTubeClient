package com.example.cld.youtueclient.dataLayer

import io.reactivex.Observable

interface YouTubeRepository {
    fun searchVideo(queryText: String) : Observable<Array<SearchListItem>>
    fun getComments(videoId: String) : Observable<Array<Comment>>
    fun getSubscriptionsForChanel(channelId:String,accessToken:String) : Observable<Array<Channel>>
}
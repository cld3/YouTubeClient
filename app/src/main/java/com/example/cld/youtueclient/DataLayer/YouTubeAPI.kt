package com.example.cld.youtueclient.dataLayer

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("search")
    fun searchVideo(
            @Query("part") part: String = "snippet",
            @Query("q") queryText: String,
            @Query("type") type: String = "video",
            @Query("key") apiKey: String = YOUTUBE_API_KEY
    ): Observable<Array<SearchListItem>>

    @GET("commentThreads")
    fun getComments(
            @Query("part") part: String = "snippet",
            @Query("videoId") videoId: String,
            @Query("key") apiKey: String = YOUTUBE_API_KEY
    ): Observable<Array<Comment>>

    @GET("subscriptions")
    fun getSubscriptionsForChanel(
            @Query("part") part: String = "snippet",
            @Query("channelId") channelId: String,
            @Query("key") accessToken: String
    ): Observable<Array<Channel>>
}

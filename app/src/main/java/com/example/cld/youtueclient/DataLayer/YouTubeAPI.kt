package com.example.cld.youtueclient.DataLayer

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
}

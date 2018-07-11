package com.example.cld.youtueclient.dataLayer

import java.io.Serializable

class SearchListItem(
        var videoId: String,
        var publishedAt: String,
        var channelId: String,
        var title: String,
        var description: String,
        var channelTitle: String,
        var imageUrl: String
) : Serializable {
}
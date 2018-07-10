package com.example.cld.youtueclient.dataLayer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SearchListItemDeserializer : JsonDeserializer<Array<SearchListItem>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<SearchListItem> {
        val items = json?.asJsonObject?.getAsJsonArray("items")
        val searchListItems = mutableListOf<SearchListItem>()
        for (i in 0 until items?.size()!!) {
            with(items[i].asJsonObject) {
                val id: String = getAsJsonObject("id").getAsJsonPrimitive("videoId").asString
                val publishedAt = getAsJsonObject("snippet").getAsJsonPrimitive("publishedAt").asString
                val channelId = getAsJsonObject("snippet").getAsJsonPrimitive("channelId").asString
                val title = getAsJsonObject("snippet").getAsJsonPrimitive("title").asString
                val description = getAsJsonObject("snippet").getAsJsonPrimitive("description").asString
                val channelTitle = getAsJsonObject("snippet").getAsJsonPrimitive("channelTitle").asString
                val imageUrl = getAsJsonObject("snippet").getAsJsonObject("thumbnails")
                        .getAsJsonObject("high")
                        .getAsJsonPrimitive("url").asString
                searchListItems.add(SearchListItem(id, publishedAt, channelId, title, description, channelTitle, imageUrl))
            }
        }
        return searchListItems.toTypedArray()
    }
}
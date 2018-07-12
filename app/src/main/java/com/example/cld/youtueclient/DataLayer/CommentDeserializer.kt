package com.example.cld.youtueclient.dataLayer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CommentDeserializer : JsonDeserializer<Array<Comment>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<Comment> {
        val items = json?.asJsonObject?.getAsJsonArray("items")
        val comments = mutableListOf<Comment>()
        for (i in 0 until items?.size()!!) {
            with(items[i].asJsonObject) {
                val text = getAsJsonObject("snippet")
                        .getAsJsonObject("topLevelComment")
                        .getAsJsonObject("snippet")
                        .getAsJsonPrimitive("textDisplay")
                        .toString()
                val authorDisplayName = getAsJsonObject("snippet")
                        .getAsJsonObject("topLevelComment")
                        .getAsJsonObject("snippet")
                        .getAsJsonPrimitive("authorDisplayName")
                        .toString()
                val authorProfileImageUrl = getAsJsonObject("snippet")
                        .getAsJsonObject("topLevelComment")
                        .getAsJsonObject("snippet")
                        .getAsJsonPrimitive("authorProfileImageUrl")
                        .toString()
                val publishedAt = getAsJsonObject("snippet")
                        .getAsJsonObject("topLevelComment")
                        .getAsJsonObject("snippet")
                        .getAsJsonPrimitive("publishedAt")
                        .toString()
                comments.add(Comment(text,authorDisplayName,authorProfileImageUrl,publishedAt))
            }
        }
        return comments.toTypedArray()
    }
}
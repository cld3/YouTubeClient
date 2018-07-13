package com.example.cld.youtueclient.dataLayer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ChannelDeserializer: JsonDeserializer<Array<Channel>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<Channel> {
        val items = json?.asJsonObject?.getAsJsonArray("items")
        val channels = mutableListOf<Channel>()
        for (i in 0 until items?.size()!!) {
            with(items[i].asJsonObject) {

                val title = getAsJsonObject("snippet").getAsJsonPrimitive("title").asString
                val description = getAsJsonObject("snippet").getAsJsonPrimitive("description").asString

                val imageUrl = getAsJsonObject("snippet").getAsJsonObject("thumbnails")
                        .getAsJsonObject("high")
                        .getAsJsonPrimitive("url").asString
                channels.add(Channel(title,description,imageUrl))
            }
        }
        return channels.toTypedArray()
    }
}
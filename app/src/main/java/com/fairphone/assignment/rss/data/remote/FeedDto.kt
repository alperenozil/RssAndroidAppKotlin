package com.fairphone.assignment.rss.data.remote

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class FeedDto(
    @Element
    val channel: Channel
) {
    @Xml(name = "channel")
    data class Channel(
        @PropertyElement
        val title: String,
        @PropertyElement
        val description: String,
        @PropertyElement
        val lastBuildDate: String,
        @Element
        val item: List<Item>
    ) {
        @Xml(name = "item")
        data class Item(
            @PropertyElement
            val title: String,
            @PropertyElement
            val link: String,
            @PropertyElement(name = "dc:creator")
            val creator: String,
            @PropertyElement
            val pubDate: String,
            @PropertyElement
            val description: String,
            @PropertyElement(name = "content:encoded")
            val content: String,
        )
    }
}
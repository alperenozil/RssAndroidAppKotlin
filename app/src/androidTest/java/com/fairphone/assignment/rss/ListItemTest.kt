package com.fairphone.assignment.rss

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.paparazzi.Paparazzi
import com.fairphone.assignment.rss.data.local.FeedEntity
import com.fairphone.assignment.rss.ui.composable.ListItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListItemTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun listItemRendersCorrectly() {
        paparazzi.snapshot {
            ListItem(
                item = FeedEntity(
                    id = 1,
                    creator = "creator",
                    pubDate = "pub",
                    title = "title",
                    description = "desc",
                    link = "link",
                    isRead = true
                ),
                setUrl = { _,_ -> }
            )
        }
    }
}
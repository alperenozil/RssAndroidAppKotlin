package com.fairphone.assignment.rss.ui.composable

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.fairphone.assignment.rss.R
import com.fairphone.assignment.rss.data.local.FeedEntity

@Composable
fun ListItem(item: FeedEntity, setUrl: (String, FeedEntity) -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_medium)))
            .background(Color.LightGray)
            .padding(dimensionResource(R.dimen.padding_medium))
            .clickable { shareContent(context, item.description) }
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            text = item.title,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.font_size_large).value.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            text = item.description,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.font_size_medium).value.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_small)).clickable { setUrl(item.link,
                FeedEntity(
                    id = item.id,
                    creator = item.creator,
                    pubDate = item.pubDate,
                    title = item.title,
                    description = item.description,
                    link = item.link,
                    isRead = true
                )
            ) },
            text = "Click for more info " + if (item.isRead) "✅" else "\uD83D\uDD18",
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.font_size_small).value.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Right
            )
        )


    }

}

fun shareContent(context: Context, content: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, content)
    }
    val chooser = Intent.createChooser(intent, "Share")
    ContextCompat.startActivity(context, chooser, null)
}

@Composable
@Preview
fun ListItem_Preview() {
    ListItem(
        FeedEntity(
            id = 1,
            creator = "cre",
            pubDate = "pub",
            title = "title",
            description = "desc",
            link = "link",
            isRead = false
        ),
        setUrl = { _,_ ->  },
    )
}
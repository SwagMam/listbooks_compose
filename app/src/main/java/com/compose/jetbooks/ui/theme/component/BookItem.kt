package com.compose.jetbooks.ui.theme.component

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.jetbooks.ui.theme.JetBooksTheme

@Composable
fun BookItem(
    author: String,
    logo:Int,
    type:String,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier
            .width(180.dp)
            .height(240.dp)
            .shadow(8.dp)
    ) {
        Column(
            modifier = modifier
                .padding(8.dp),
        ) {
            AsyncImage(
                model = logo,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleAuthor(
                titleAuthor = author,
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = type,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TitleAuthor(
    titleAuthor: String,
    modifier: Modifier = Modifier,
) {
    Box {
        Card(
            modifier = modifier
                .width(100.dp)
                .height(20.dp)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(4.dp),
                text = titleAuthor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun RewardItemPreview() {
    JetBooksTheme {
        BookItem(
            "Henry Manampiring",
            0,
            "Self Improvement"
        )
    }
}
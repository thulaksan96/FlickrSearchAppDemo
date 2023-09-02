package com.example.image_search_ui.imagelistscreens.composablecomponenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.image_search_ui.imagelistscreens.Photo

@Composable
fun ImageListCard(
    modifier: Modifier = Modifier,
    item: Photo,
    navigateToDetailsScreen: (String) -> Unit
) {
    Card(modifier = modifier) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navigateToDetailsScreen(item.id)
                }
        ) {

            Image(
                painter = rememberAsyncImagePainter(item.url),
                contentDescription = "Image from URL",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            OwnerIconAndNameRow(name = item.ownerName, iconUrl = item.ownerIconUrl)

        }


    }

}
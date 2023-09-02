package com.example.image_search_ui.imagelistscreens.composablecomponenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun OwnerIconAndNameRow(
    name: String,
    iconUrl: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = rememberAsyncImagePainter(iconUrl),
            contentDescription = "Image from URL",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Text(modifier = Modifier.padding(start = 2.dp), text = name)
    }

}
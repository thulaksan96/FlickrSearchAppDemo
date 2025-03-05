package com.example.image_search_ui.imagelistscreens.composablecomponenets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.image_search_ui.imagelistscreens.Photo
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingFourth
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingHalf

@Composable
internal fun ImageListCard(
    modifier: Modifier = Modifier,
    item: Photo,
    navigateToDetailsScreen: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navigateToDetailsScreen(item.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = PaddingFourth),
        shape = RoundedCornerShape(PaddingHalf)
    ) {
        Column {
            AsyncImage(
                model = item.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = PaddingHalf, topEnd = PaddingHalf)),
                contentScale = ContentScale.Crop
            )
            OwnerIconAndNameRow(name = item.ownerName, iconUrl = item.ownerIconUrl)
        }
    }
}

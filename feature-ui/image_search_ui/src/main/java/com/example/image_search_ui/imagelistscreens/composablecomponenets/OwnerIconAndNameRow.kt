package com.example.image_search_ui.imagelistscreens.composablecomponenets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingDefault
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingEighth
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingHalf
import com.thulaksan.ui.theme.dimens.AppDimensions.UserIconSize

@Composable
internal fun OwnerIconAndNameRow(
    name: String,
    iconUrl: String,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = PaddingHalf)
            .padding(bottom = PaddingDefault, top = PaddingHalf),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = iconUrl,
            contentDescription = null,
            modifier = Modifier
                .size(UserIconSize)
                .padding(PaddingHalf)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(PaddingHalf)),
            contentScale = ContentScale.Crop
        )
        Text(modifier = Modifier.padding(start = PaddingEighth), text = name)
    }
}

package com.example.image_search_ui.imagedetailsscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.image_search_ui.imagelistscreens.composablecomponenets.ImageListCard
import com.example.image_search_ui.imagelistscreens.composablecomponenets.OwnerIconAndNameRow
import com.thulaksan.ui.R
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingDefault
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingHalf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageDetailsScreen(
    uiState: PhotoDetailsUiState,
    onBackIconClick: () -> Unit,
) {
    var userGalleyVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.label_details)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackIconClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = rememberAsyncImagePainter(uiState.photoData.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(PaddingHalf)
                    .background(Color.Gray),
                contentScale = ContentScale.Fit
            )
            OwnerIconAndNameRow(
                name = uiState.photoData.ownerName,
                iconUrl = uiState.photoData.ownerIconUrl
            )
            Text(
                text = stringResource(R.string.label_date_taken, uiState.photoData.dateTaken),
                modifier = Modifier.padding(PaddingHalf)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingDefault),
                onClick = {
                    userGalleyVisible = !userGalleyVisible
                },
                content = {
                    Text(text = stringResource(R.string.label_see_more_from_creator))
                },
            )
            AnimatedVisibility(visible = userGalleyVisible) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        itemsIndexed(uiState.photoList) { index, item ->
                            ImageListCard(
                                modifier = Modifier.padding(PaddingHalf),
                                item = item,
                                navigateToDetailsScreen = {}
                            )
                        }
                    }
                )
            }
        }
    }
}
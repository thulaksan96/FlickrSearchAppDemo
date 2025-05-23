package com.example.image_search_ui.imagelistscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.image_search_ui.imagelistscreens.composablecomponenets.ImageListCard
import com.thulaksan.ui.theme.dimens.AppDimensions.PaddingHalf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageListScreen(
    uiState: PhotoUiState,
    navigateToDetailsScreen: (String) -> Unit,
    changeSearchQuery: (String) -> Unit,
    search: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingHalf),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(end = PaddingHalf),
                value = uiState.searchQuery,
                placeholder = { Text(text = "Please enter a search term") },
                onValueChange = { newValue -> changeSearchQuery(newValue) }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), onClick = search, content = {
                    Text(text = "Search")
                })
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(uiState.photoList) {
                    ImageListCard(
                        modifier = Modifier.padding(PaddingHalf),
                        item = it,
                        navigateToDetailsScreen = navigateToDetailsScreen
                    )
                }
            }
        )
    }
}
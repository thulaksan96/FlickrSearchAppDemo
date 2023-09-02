package com.example.flickr_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreen(vm: ImageListScreenViewmodel = koinViewModel()) {

    val uiState = vm.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        Row {
            TextField(
                value = uiState.value.searchQuery,
                onValueChange = { newValue -> vm.changeSearchQuery(newValue) })

            Button(onClick = { vm.search() }) {

            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {

                itemsIndexed(uiState.value.photoList) { index, item ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .background(Color.Gray)
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(item.url),
                            contentDescription = "Image from URL",
                            modifier = Modifier
                                .size(200.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(text = item.title)

                    }

                }

            }
        )

    }

}
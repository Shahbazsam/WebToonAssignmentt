package com.example.webtoonassignmentt.ui.screens


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.webtoonassignmentt.data.WebToon
import com.example.webtoonassignmentt.data.webtoon
import com.example.webtoonassignmentt.ui.FavoriteManager
import com.example.webtoonassignmentt.ui.FavoriteViewModel

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    viewModel: FavoriteViewModel,
    onItemClick: (WebToon) -> Unit
) {
    val favoriteWebtoons = FavoriteManager.favoriteWebtoons.value.keys.mapNotNull { title ->
        webtoon.find { it.title == title }
    }

    LazyColumn(contentPadding = paddingValues) {
        items(favoriteWebtoons) { webtoonItem ->
            WebToonItem(
                webtoon = webtoonItem,
                modifier = Modifier.padding(16.dp),
                onItemClick = { onItemClick(webtoonItem) }
            )
        }
    }
}
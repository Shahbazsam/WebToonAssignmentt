package com.example.webtoonassignmentt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.webtoonassignmentt.R
import com.example.webtoonassignmentt.data.WebToon
import com.example.webtoonassignmentt.dataBase.FavoriteDatabase
import com.example.webtoonassignmentt.ui.FavoriteManager
import com.example.webtoonassignmentt.ui.FavoriteViewModel
import com.example.webtoonassignmentt.ui.FavoriteViewModelFactory


@Composable
fun DetailScreen(
    webToon: WebToon,
    viewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(LocalContext.current.applicationContext as FavoriteDatabase)
    )
) {

    val isFavorite = FavoriteManager.favoriteWebtoons.value.containsKey(webToon.title)
    var rating by remember {
        mutableStateOf(FavoriteManager.favoriteWebtoons.value[webToon.title] ?: 0)
    }

    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Text(

            text = stringResource(id = webToon.title),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.fillMaxWidth(1f),
            painter = painterResource(id = webToon.image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                modifier = Modifier.size(50.dp),
                rating = rating,
                starColor = Color.Yellow
            ) { newRating ->
                rating = newRating
            }

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Add to Favorites",
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        viewModel.onFavoriteClick(webToon, rating)

                    },
                tint = if (isFavorite) Color.Red else Color.LightGray
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(

            text = stringResource(id = webToon.detailedDescription),
            style = MaterialTheme.typography.bodyMedium
        )
    }


}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating : Int = 0,
    stars : Int = 5,
    starColor : Color = Color.Yellow,
    onRatingChange : (Int) -> Unit
) {
    Row {
        for(index in 1 .. stars) {
            Icon(
                modifier = modifier.clickable { onRatingChange(index.toInt()) },
                imageVector = if(index <= rating) {
                    Icons.Rounded.Star
                }else{
                    Icons.Rounded.StarOutline
                },
                tint = starColor,
                contentDescription = null
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val webtoon = WebToon(R.drawable.photo1 , R.string.title1 , R.string.description1 , R.string.detailed_description1)
    //DetailScreen(webtoon = webtoon)
}
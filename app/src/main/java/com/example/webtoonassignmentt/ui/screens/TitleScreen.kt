package com.example.webtoonassignmentt.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.webtoonassignmentt.data.WebToon
import com.example.webtoonassignmentt.data.webtoon
import com.example.webtoonassignmentt.dataBase.FavoriteDatabase
import com.example.webtoonassignmentt.ui.FavoriteViewModel
import com.example.webtoonassignmentt.ui.FavoriteViewModelFactory


@Composable
fun WebToonApp(db : FavoriteDatabase) {

    val navController = rememberNavController()
    val viewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(db) // Provide the database to the factory
    )
    Scaffold(
        topBar = {
            TopAppBar(navController)
        }
    ) { innerPadding ->
        NavigationComponent(navController, innerPadding , viewModel)
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController, // Corrected type
    paddingValues: PaddingValues,
    viewModel: FavoriteViewModel
) {
    NavHost(navController = navController, startDestination = "webtoonList") {
        composable("webtoonList") {
            WebtoonListScreen(paddingValues, navController)
        }
        composable("detailScreen/{webtoonTitle}") { backStackEntry ->
            val webtoonTitle = backStackEntry.arguments?.getString("webtoonTitle")?.toIntOrNull() ?: 0
            val selectedWebtoon = webtoon.find { it.title == webtoonTitle }
            selectedWebtoon?.let { webtoon ->
                DetailScreen(webToon = webtoon, viewModel = viewModel)
            }
        }
        composable("favoriteScreen") {
            FavoriteScreen(paddingValues, viewModel) { webtoon ->
                navController.navigate("detailScreen/${webtoon.title}")
            }
        }
    }
}

@Composable
fun WebtoonListScreen(paddingValues: PaddingValues, navController: NavController) {
    LazyColumn(contentPadding = paddingValues) {
        items(webtoon) { webtoonItem ->
            WebToonItem(
                webtoon = webtoonItem,
                modifier = Modifier.padding(16.dp),
                onItemClick = {
                    navController.navigate("detailScreen/${webtoonItem.title}")
                }
            )
        }
    }
}

@Composable
fun WebToonItem(
    webtoon : WebToon,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onItemClick() }
                .padding(8.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                WebToonIcon(webtoon.image)
                WebToonInfo(webtoon.title)
                Spacer(modifier = Modifier.weight(1f))
                ToonItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded)
            {
                ToonDescription(
                    description = webtoon.description,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )

                )
            }
        }
    }
}

@Composable
private fun ToonItemButton(
    expanded : Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick ,
        modifier = Modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
        )

    }

}

@Composable
fun ToonDescription(
    @StringRes description : Int,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Description:",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun WebToonIcon(
    @DrawableRes icon : Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = icon),
        contentDescription = null
    )
}

@Composable
fun WebToonInfo(
    @StringRes title : Int,
    modifier: Modifier = Modifier
) {
    Column( modifier = Modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "WEBTOON",
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }

        },
        actions = {
            IconButton(onClick = { navController.navigate("favoriteScreen") }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorites"
                )
            }
        }

    )
}
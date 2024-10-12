package com.example.webtoonassignmentt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.webtoonassignmentt.dataBase.FavoriteDatabase
import com.example.webtoonassignmentt.ui.screens.WebToonApp
import com.example.webtoonassignmentt.ui.theme.WebToonAssignmenttTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebToonAssignmenttTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WebToonApp(db)
                }
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun WebToonPreview() {
    WebToonAssignmenttTheme {
       // WebToonApp()
    }
}
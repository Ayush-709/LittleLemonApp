package com.example.littlelemonapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemonapplication.ui.theme.LittleLemonApplicationTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    private val httpClient by lazy {
        HttpClient(Android){
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Menu.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonApplicationTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserOnBoarding(navController, sharedPreferences, database.menuItemDao())
                    Navigation(navController, sharedPreferences, database.menuItemDao())
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            // add code here
            if(database.menuItemDao().isEmpty()){
                val response = fetchMenu()
                Log.println(Log.INFO, "checkingAPIRespnse", response.toString())
                saveMenuToDatabase(response)
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient.get(url).body<MenuNetworkData>().menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

}

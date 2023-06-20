package com.example.littlelemonapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonapplication.ui.theme.LittleLemonApplicationTheme

class MainActivity : ComponentActivity() {

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
                    UserOnBoarding(navController, sharedPreferences)
                    Navigation(navController, sharedPreferences)
                }
            }
        }
    }
}

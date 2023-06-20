package com.example.littlelemonapplication

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(){

    val sharedPreferences = LocalContext.current.getSharedPreferences("myPrefs",Context.MODE_PRIVATE)
    val username = sharedPreferences.getString("firstName", "")?:""
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
       Text(text = username)
    }
}
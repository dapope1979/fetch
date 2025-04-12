package com.example.fetchtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fetchtest.ui.FetchTestApp
import com.example.fetchtest.ui.theme.FetchTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTestTheme {
                FetchTestApp()
            }
        }
    }
}


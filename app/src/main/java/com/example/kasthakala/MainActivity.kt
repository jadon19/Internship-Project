package com.example.kasthakala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kasthakala.navigation.NavGraph
import com.example.kasthakala.ui.theme.KashtaKalaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KashtaKalaTheme {
                NavGraph()
            }
        }
    }
}
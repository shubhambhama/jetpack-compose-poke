package com.example.jetpackcomposeblog_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeblog_app.pokemon.presentation.dash.component.PokemonCategoryScreen
import com.example.jetpackcomposeblog_app.pokemon.presentation.util.Screen
import com.example.jetpackcomposeblog_app.ui.theme.JetpackComposeBlogAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBlogAppTheme {
                PokemonNavigations()
            }
        }
    }
}

@Composable
private fun PokemonNavigations() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DashboardScreen.route) {
        composable(route = Screen.DashboardScreen.route) {
            PokemonCategoryScreen(navController)
        }
    }
}
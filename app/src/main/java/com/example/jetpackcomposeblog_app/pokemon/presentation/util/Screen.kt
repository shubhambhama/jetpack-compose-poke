package com.example.jetpackcomposeblog_app.pokemon.presentation.util

sealed class Screen(val route: String) {
    object DashboardScreen: Screen("pokemon_dashboard_screen")
    object MoreDetailScreen: Screen("more_detail_screen")
}

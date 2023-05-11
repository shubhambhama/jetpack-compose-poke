package com.example.jetpackcomposeblog_app.pokemon.presentation.dash.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.jetpackcomposeblog_app.R
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonData
import com.example.jetpackcomposeblog_app.pokemon.presentation.dash.DashboardViewModel

@Composable
fun PokemonCategoryScreen(navController: NavHostController?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopHeader {
                // User Profile Clicked
            }
            PokemonCategoryScreen()
        }
    }
}

@Composable
fun TopHeader(onUserProfileClickListener: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Android Logo",
                modifier = Modifier
                    .wrapContentWidth()
                    .height(42.dp)
                    .padding(start = 8.dp)
            )
            Image(painter = painterResource(R.drawable.ic_profile),
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onUserProfileClickListener.invoke() })
        }
    }
}

@Composable
fun PokemonCategoryScreen(viewModel: DashboardViewModel = hiltViewModel(), onClickItemListener: (Int) -> Unit) {
    val pokemons = viewModel.pokemonState.value
    LazyColumn {
        items(pokemons) { pokemon ->
            PokemonCategory(pokemon) {
                // Get the id of the item clicked.
            }
        }
    }
}

@Composable
fun PokemonCategory(pokemon: PokemonData, onClickItemListener: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClickItemListener.invoke(pokemon.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        val image = rememberImagePainter(data = pokemon.imageUrl)
        Row(modifier = Modifier.animateContentSize()) {
            Image(painter = image, contentDescription = pokemon.name)
        }
    }
}
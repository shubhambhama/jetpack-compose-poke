package com.example.jetpackcomposeblog_app.pokemon.presentation.dash.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetpackcomposeblog_app.R
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonData
import com.example.jetpackcomposeblog_app.pokemon.presentation.dash.DashboardViewModel
import com.example.jetpackcomposeblog_app.ui.theme.cardBackground

@Composable
fun PokemonCategoryScreen(navController: NavHostController?) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopHeader {
                // User Profile Clicked
            }
            PokemonCategoryList(configuration = configuration, density = density) {
                // Get the id of the item clicked pokemon
            }
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
fun PokemonCategoryList(viewModel: DashboardViewModel = hiltViewModel(), configuration: Configuration,
                        density: Density, onClickItemListener: (Int) -> Unit) {
    val pokemons = viewModel.pokemonState.value
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        val itemCount = if (pokemons.size % 2 == 0) {
            pokemons.size / 2
        } else {
            pokemons.size + 1
        }
        items(itemCount) { pokemonDex ->
            PokemonRow(pokemonDex, pokemons, viewModel, configuration, density)
        }
    }
}

@Composable
fun PokemonCategory(pokemon: PokemonData, modifier: Modifier, viewModel: DashboardViewModel, configuration: Configuration,
                    density: Density) {
    val coverImageHeight = with(density) { (configuration.screenHeightDp * 0.35f).toDp() }
    val pokemonImageSize = with(density) { (configuration.screenHeightDp * 0.25f).toDp() }
    val desiredTextSizeSp = with(density) { (configuration.screenWidthDp * 0.13f).toSp() }

    val defaultCoverColor = MaterialTheme.colors.background
    var coverColor by remember {
        mutableStateOf(defaultCoverColor)
    }
    Box(contentAlignment = Alignment.TopCenter, modifier = modifier
            .shadow(2.dp, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .wrapContentSize()
            .background(cardBackground())) {
        val context = LocalContext.current
        val request = ImageRequest.Builder(context).data(pokemon.imageUrl).apply(block = fun ImageRequest.Builder.() {
            listener { _, result ->
                viewModel.imagePalette(result.drawable) { color ->
                    coverColor = color
                }
            }
            transformations(CircleCropTransformation())
        }).build()
        val image = rememberAsyncImagePainter(request)
        Column {
            CoverImage(modifier = Modifier
                    .fillMaxSize()
                    .height(coverImageHeight), coverColors = listOf(coverColor, coverColor, defaultCoverColor))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
                    CircleImage(image, pokemon.name, pokemonImageSize)
                    Text(text = pokemon.name.replaceFirstChar { it.uppercase() }, fontSize = desiredTextSizeSp, textAlign = TextAlign.Center, modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .offset(y = -(pokemonImageSize/2)))
                }
            }
        }
    }
}

@Composable
fun PokemonRow(pokemonIndex: Int, entries: List<PokemonData>, viewModel: DashboardViewModel, configuration: Configuration,
               density: Density) {
    Column {
        Row {
            PokemonCategory(entries[pokemonIndex * 2], Modifier.weight(1f), viewModel, configuration = configuration, density = density)
            Spacer(modifier = Modifier.width(8.dp))
            if (entries.size >= pokemonIndex * 2 + 2) {
                PokemonCategory(entries[pokemonIndex * 2 + 1], Modifier.weight(1f), viewModel, configuration = configuration, density = density)
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CoverImage(modifier: Modifier, coverColors: List<Color>) {
    Box(modifier = modifier) {
        Canvas(modifier = modifier) {
            val clipPath = Path().apply {
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                lineTo(size.width, 0F)
                close()
            }
            clipPath(clipPath) {
                val gradient = Brush.linearGradient(colors = coverColors, start = Offset(0f, 0f), end = Offset(0f, size.height))
                drawRoundRect(brush = gradient, size = size, cornerRadius = CornerRadius(5.dp.toPx()))
            }
        }
    }
}

@Composable
fun CircleImage(image: AsyncImagePainter, pokemonName: String, pokemonImageSize: Dp) {
    val imageSize = remember(image) {
        mutableStateOf(Pair(pokemonImageSize, pokemonImageSize))
    }
    val isDarkTheme = isSystemInDarkTheme()
    Card(modifier = Modifier
            .size(pokemonImageSize)
            .offset(y = -(imageSize.value.second / 2))
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                imageSize.value = Pair(placeable.width
                        .toFloat()
                        .toDp(), placeable.height
                        .toFloat()
                        .toDp())
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            }, shape = CircleShape, border = BorderStroke(width = 1.5.dp, color = if (isDarkTheme) Color.White else Color.Black)) {
        Image(painter = image, contentDescription = pokemonName, modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .align(Alignment.CenterHorizontally))
    }
}
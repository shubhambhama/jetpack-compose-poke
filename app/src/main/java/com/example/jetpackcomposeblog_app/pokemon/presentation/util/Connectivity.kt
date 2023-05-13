package com.example.jetpackcomposeblog_app.pokemon.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun CheckForNetwork(onContent: () -> Unit, onError: @Composable () -> Unit) {
    var isNetworkAvailable by remember {
        mutableStateOf(false)
    }
    val connnectionManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = remember {
        object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                isNetworkAvailable = false
            }

            override fun onAvailable(network: Network) {
                isNetworkAvailable = true
            }
        }
    }

    DisposableEffect(Unit) {
        connnectionManager.registerDefaultNetworkCallback(networkCallback)
        onDispose {
            connnectionManager.unregisterNetworkCallback(networkCallback)
        }
    }

    if (isNetworkAvailable) onContent.invoke()
    else onError.invoke()
}
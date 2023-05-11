package com.example.jetpackcomposeblog_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeblog_app.ui.theme.JetpackComposeBlogAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBlogAppTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier =  Modifier.fillMaxSize()) {
                        TopHeader {
                            showSnackbar(snackbarHostState, "User Profile Clicked")
                        }

                        Box(modifier = Modifier.fillMaxSize()) {
                            SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopHeader(onUserProfileClickListener: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
            Image(painter = painterResource(R.drawable.ic_logo), contentDescription = "Android Logo", modifier = Modifier
                    .wrapContentWidth()
                    .height(42.dp)
                    .padding(start = 8.dp))
            Image(painter = painterResource(R.drawable.ic_profile), contentDescription = "User Profile", modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onUserProfileClickListener.invoke() })
        }
    }
}

fun showSnackbar(snackbarHostState: SnackbarHostState, message: String) {
    CoroutineScope(Dispatchers.Main).launch {
        snackbarHostState.showSnackbar(message)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeBlogAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TopHeader {}
        }
    }
}
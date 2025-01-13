package com.example.quadb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quadb.Data.ApiService
import com.example.quadb.Data.Item
import com.example.quadb.ViewModel.ItemRepository
import com.example.quadb.screens.HomePage
import com.example.quadb.ui.theme.QuadBTheme
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrofit setup for API calls
        val apiService = Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com") // Correct API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val repository = ItemRepository(apiService)

        enableEdgeToEdge()

        setContent {
            QuadBTheme {
                MainContent(repository)
            }
        }
    }
}

@Composable
fun MainContent(repository: ItemRepository) {
    var showSplash by remember { mutableStateOf(true) }
    var items: List<Item>? by remember { mutableStateOf(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            // Fetch data from the API while showing the splash screen
            items = repository.fetchShows("all")
        } catch (e: Exception) {
            errorMessage = e.message
        }
        delay(2000) // Ensure splash screen lasts for a while
        showSplash = false
    }

    when {
        showSplash -> SplashScreen()
        errorMessage != null -> ErrorScreen(errorMessage!!)
        else -> HomePage(apiResponses= items ?: emptyList())
    }
}

@Composable
fun SplashScreen() {
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(durationMillis = 800))
        delay(1200)
        alpha.animateTo(0f, animationSpec = tween(durationMillis = 1000))
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(color = Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.mipmap.netflix_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(150.dp)
                    .graphicsLayer(alpha = alpha.value)
            )
        }
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $errorMessage",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
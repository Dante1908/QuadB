package com.example.quadb.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.quadb.Data.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(apiResponses: List<Item>) {
    var searchText by remember { mutableStateOf("") }
    var filteredResponses by remember { mutableStateOf(apiResponses) }
    val context = LocalContext.current // Get the current context

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                filteredResponses = apiResponses.filter { response ->
                    response.show.name.contains(it, ignoreCase = true) ||
                            response.show.language.contains(it, ignoreCase = true) ||
                            response.show.genres.any { genre -> genre.contains(it, ignoreCase = true) }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search shows...") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // List of Clickable Items
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(color = Color.DarkGray),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredResponses) { response ->
                ClickableItemBox(item = response, onClick = {
                    // Redirect to the show's website
                    val url = response.show.url
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun ClickableItemBox(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.LightGray)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Textual Information on the Left
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Name: ${item.show.name}",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "Language: ${item.show.language}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Genres: ${item.show.genres.joinToString(", ")}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Status: ${item.show.status}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Show Image on the Right
            Image(
                painter = rememberImagePainter(data = item.show.image?.medium),
                contentDescription = "Show Image",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray), // Placeholder background
                contentScale = ContentScale.Crop
            )
        }
    }
}


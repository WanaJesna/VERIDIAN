package com.jessy.veridian.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jessy.veridian.R

@Composable
fun HomeScreen(navController: NavController) {
    val softGreen = Color(0xFFDFF5E3) // Light pastel green background
    val darkGreen = Color(0xFF1E5631) // Darker green for text and icons

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = softGreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Welcome Text
            Text(
                text = "Welcome to Veridian",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = darkGreen
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your companion in study and wellbeing",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = darkGreen.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Feature Buttons
            FeatureButton("Study Tracker", R.drawable.tracker) {
                navController.navigate("tracker")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FeatureButton("Flashcards", R.drawable.flashcard) {
                navController.navigate("flashcards")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FeatureButton("Mood Check-in", R.drawable.ic_mood) {
                navController.navigate("mood")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FeatureButton("Contact Us", R.drawable.contact) {
                navController.navigate("contact")
            }
        }
    }
}

@Composable
fun FeatureButton(label: String, iconRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = Color(0xFF1E5631), // Dark green for icons
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E5631) // Dark green for text
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
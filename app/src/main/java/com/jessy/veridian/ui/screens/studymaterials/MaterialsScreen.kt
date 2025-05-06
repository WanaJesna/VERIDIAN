package com.jessy.veridian.ui.screens.studymaterials

//access materials online.



import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun MaterialsScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF4CAF50), Color(0xFF2E7D32)) // Matching splash screen colors
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Study Materials",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // List of Study Materials
            StudyMaterialItem(
                title = "Mathematics Notes",
                url = "https://example.com/mathematics",
                context = context
            )

            Spacer(modifier = Modifier.height(8.dp))

            StudyMaterialItem(
                title = "Physics Notes",
                url = "https://example.com/physics",
                context = context
            )

            Spacer(modifier = Modifier.height(8.dp))

            StudyMaterialItem(
                title = "Chemistry Notes",
                url = "https://example.com/chemistry",
                context = context
            )

            Spacer(modifier = Modifier.height(8.dp))

            StudyMaterialItem(
                title = "Biology Notes",
                url = "https://example.com/biology",
                context = context
            )
        }
    }
}

@Composable
fun StudyMaterialItem(title: String, url: String, context: android.content.Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                // Open the URL in a browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2E7D32) // Dark green for text
            )
        }
    }
}
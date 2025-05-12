package com.jessy.veridian.ui.screens.assignment

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jessy.veridian.R
import com.jessy.veridian.model.Assignment
import com.jessy.veridian.viewmodel.AssignmentViewModel
import com.jessy.veridian.navigation.ROUT_ADD_ASSIGNMENT
import com.jessy.veridian.navigation.ROUT_EDIT_ASSIGNMENT
import com.jessy.veridian.navigation.ROUT_ASSIGNMENT_LIST
import com.jessy.veridian.navigation.editAssignmentRoute
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentListScreen(navController: NavController, viewModel: AssignmentViewModel) {
    val assignmentList by viewModel.allAssignments.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts =assignmentList.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Assignments", fontSize = 20.sp) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Assignment List") },
                                onClick = {
                                    navController.navigate(ROUT_ASSIGNMENT_LIST)
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Add Assignment") },
                                onClick = {
                                    navController.navigate(ROUT_ADD_ASSIGNMENT)
                                    showMenu = false
                                }
                            )
                        }
                    }
                )

                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    placeholder = { Text("Search assignments...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                    )
                )
            }
        },
        bottomBar = { BottomNavigationBar2(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(filteredProducts.size) { index ->
                    AssignmentItem2(navController, filteredProducts[index], viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AssignmentItem2(navController: NavController, assignment: Assignment, viewModel: AssignmentViewModel) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                if (assignment.id != 0) {
                    navController.navigate(editAssignmentRoute(assignment.id))
                }
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Assignment Info
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = assignment.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = assignment.description,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = assignment.teacherId,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            // Buttons (Edit, Delete, Download PDF)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Edit Assignment
                    IconButton(
                        onClick = {
                            navController.navigate(editAssignmentRoute(assignment.id))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = Color.Black
                        )
                    }



                    // Download PDF
                    IconButton(
                        onClick = { generateAssignmentPDF2(context, assignment) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.download),
                            contentDescription = "Download PDF",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun generateAssignmentPDF2(context: Context, assignment: Assignment) {
    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(300, 500, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas
    val paint = android.graphics.Paint()

    paint.textSize = 16f
    paint.isFakeBoldText = true
    canvas.drawText("Assignment Details", 80f, 50f, paint)

    paint.textSize = 12f
    paint.isFakeBoldText = false
    canvas.drawText("Title: ${assignment.title}", 50f, 100f, paint)
    canvas.drawText("Description: ${assignment.description}", 50f, 120f, paint)

    pdfDocument.finishPage(page)

    val fileName = "${assignment.title}_Details.pdf"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val contentResolver = context.contentResolver
    val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

    if (uri != null) {
        try {
            val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
            if (outputStream != null) {
                pdfDocument.writeTo(outputStream)
                Toast.makeText(context, "PDF saved to Downloads!", Toast.LENGTH_LONG).show()
            }
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save PDF!", Toast.LENGTH_LONG).show()
        }
    } else {
        Toast.makeText(context, "Failed to create file!", Toast.LENGTH_LONG).show()
    }

    pdfDocument.close()
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar2(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFFA2B9A2),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ASSIGNMENT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Assignment List") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_ASSIGNMENT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Assignment") },
            label = { Text("Add") }
        )
    }
}
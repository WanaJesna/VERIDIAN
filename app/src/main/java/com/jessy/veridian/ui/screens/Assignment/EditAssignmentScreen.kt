package com.jessy.veridian.ui.screens.assignment

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jessy.veridian.navigation.ROUT_ADD_ASSIGNMENT
import com.jessy.veridian.navigation.ROUT_ASSIGNMENT_LIST
import com.jessy.veridian.viewmodel.AssignmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAssignmentScreen(assignmentId: Int?, navController: NavController, viewModel: AssignmentViewModel) {
    val context = LocalContext.current
    val assignmentList by viewModel.allAssignments.observeAsState(emptyList())

    // Ensure assignmentId is valid
    val assignment = remember(assignmentList) { assignmentList.find { it.id == assignmentId } }

    // Track state variables only when assignment is found
    var title by remember { mutableStateOf(assignment?.title ?: "") }
    var description by remember { mutableStateOf(assignment?.description ?: "") }
    var teacherid by remember { mutableStateOf(assignment?.description ?: "") }
    var imagePath by remember { mutableStateOf(assignment?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    // Image picker
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Assignment") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
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
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (assignment != null) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Assignment Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = teacherid,
                    onValueChange = { teacherid = it },
                    label = { Text("Teacher id") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Assignment Description") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Display Image
                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                    contentDescription = "Assignment Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Change Image")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (title.isNotBlank() && description.isNotBlank()) {
                            viewModel.updateAssignment(
                                assignment.copy(title = title, description = description, imagePath = imagePath)
                            )
                            Toast.makeText(context, "Assignment Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Title and description cannot be empty!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Update Assignment")
                }
            } else {
                Text(text = "Assignment not found", color = MaterialTheme.colorScheme.error)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF6F6A72),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ASSIGNMENT_LIST) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Assignment List") },
            label = { Text("Assignments") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_ASSIGNMENT) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Add Assignment") },
            label = { Text("Add") }
        )
    }
}
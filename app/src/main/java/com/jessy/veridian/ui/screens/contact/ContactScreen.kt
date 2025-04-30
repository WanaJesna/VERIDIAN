package com.jessy.veridian.ui.screens.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jessy.veridian.ui.theme.softgreen




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController){

    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {

            TopAppBar(
                title = { Text("Contact") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        androidx.compose.material3.Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = softgreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = softgreen
            ){
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                       // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        //  navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "Info") },
                    label = { Text("Info") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )




            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = softgreen
            ) {
                androidx.compose.material3.Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {


                //Main Contents of the page
                Column {

                    Text(
                        text = "Need Help or Have Feedback?",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "We're here to support your learning journey.If you have any questions,suggestions, or just want to say hello ,feel free to reach out",
                        fontSize = 20.sp,
                        )

                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Email:support@veridianapp.com",
                        fontSize = 15.sp,
                    )
                    Text(
                        text = "Phone:+254 712 345678(optional)",
                        fontSize = 15.sp,
                    )
                    Text(
                        text = "Office hours: Mon-Fri,9:00 AM-5:00 PM",
                        fontSize = 15.sp,
                    )



                }















            }
        }
    )

    //End of scaffold








}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    ContactScreen(navController= rememberNavController())
}
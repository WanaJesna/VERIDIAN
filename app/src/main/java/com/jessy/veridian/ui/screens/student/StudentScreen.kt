package com.jessy.veridian.ui.screens.student


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun StudentScreen(navController: NavController){


}

@Preview(showBackground = true)
@Composable
fun StudentScreenPreview(){
    StudentScreen(navController= rememberNavController())
}

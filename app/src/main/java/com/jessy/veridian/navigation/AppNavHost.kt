package com.jessy.veridian.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jessy.veridian.data.UserDatabase
import com.jessy.veridian.ui.screens.about.AboutScreen
import com.jessy.veridian.ui.screens.home.HomeScreen
import com.jessy.veridian.repository.UserRepository
import com.jessy.veridian.ui.screens.auth.RegisterScreen
import com.jessy.veridian.ui.screens.splash.SplashScreen
import com.jessy.veridian.ui.screens.studytracker.TrackerScreen
import com.jessy.veridian.viewmodel.AuthViewModel
import com.jessy.veridian.ui.screens.auth.LoginScreen
import com.jessy.veridian.ui.screens.teacher.TeacherScreen
import com.jessy.veridian.ui.screens.materials.MaterialsScreen
import com.jessy.veridian.ui.screens.studymaterials.MaterialsScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }


        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_TRACKER) {
            TrackerScreen(navController)

        }
        composable(ROUT_ROLE) {
            TrackerScreen(navController)

        }
        composable(ROUT_STUDENT) {
            TrackerScreen(navController)

        }
        composable(ROUT_STUDYMATERIAL) {
            TrackerScreen(navController)

        }
        composable(ROUT_TRACKER) {
            TrackerScreen(navController)

        }
        composable(ROUT_TEACHER) {
            TeacherScreen(navController)

        }
        composable(ROUT_REGISTER) {
            MaterialsScreen(navController)
        }
        val appDatabase = AppDatabase.getDatabase(context)
        val userDao = appDatabase.userDao()
        val assignmentDao = appDatabase.assignmentDao()
        val feedbackDao = appDatabase.feedbackDao()


        // Initialize Room Database and Repository for Authentication


        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }

    }
}


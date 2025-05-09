package com.jessy.veridian.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jessy.veridian.data.UserDatabase
import com.jessy.veridian.repository.UserRepository
import com.jessy.veridian.ui.screens.about.AboutScreen
import com.jessy.veridian.ui.screens.auth.LoginScreen
import com.jessy.veridian.ui.screens.home.HomeScreen
import com.jessy.veridian.ui.screens.assignment.AddAssignmentScreen
import com.jessy.veridian.ui.screens.assignment.EditAssignmentScreen
import com.jessy.veridian.ui.screens.assignment.AssignmentListScreen
import com.jessy.veridian.ui.screens.roles.RoleScreen
import com.jessy.veridian.ui.screens.splash.SplashScreen
import com.jessy.veridian.ui.screens.student.StudentScreen
import com.jessy.veridian.ui.screens.studymaterials.MaterialsScreen
import com.jessy.veridian.ui.screens.studytracker.TrackerScreen
import com.jessy.veridian.ui.screens.support.ContactScreen
import com.jessy.veridian.ui.screens.support.HelpScreen
import com.jessy.veridian.ui.screens.support.SettingScreen
import com.jessy.veridian.ui.screens.teacher.TeacherScreen
import com.jessy.veridian.viewmodel.AuthViewModel
import com.jessy.veridian.viewmodel.AssignmentViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_ADD_ASSIGNMENT,
    assignmentViewModel: AssignmentViewModel= viewModel(),
) {
    val context = LocalContext.current

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


        composable(ROUT_SETTING) {
            SettingScreen(navController)
        }

        composable(ROUT_TEACHER) {
             TeacherScreen(navController)
        }


        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }

        composable(ROUT_HELP) {
            HelpScreen(navController)
        }


        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_ROLE) {
            RoleScreen(navController)
        }


        composable(ROUT_STUDENT) {
            StudentScreen(navController)
        }
        composable(ROUT_TRACKER) {
            TrackerScreen(navController)
        }


        composable(ROUT_STUDYMATERIAL) {
            MaterialsScreen(navController)
        }
        //AUTHENTICATION

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
        // PRODUCTS
        composable(ROUT_ADD_ASSIGNMENT) {
            AddAssignmentScreen(navController, assignmentViewModel)
        }

        composable(ROUT_ASSIGNMENT_LIST) {
            AssignmentListScreen(navController, assignmentViewModel)
        }

        composable(
            route = ROUT_EDIT_ASSIGNMENT,
            arguments = listOf(navArgument("assignmentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val assignmentId = backStackEntry.arguments?.getInt("assignmentId")
            if (assignmentId != null) {
                EditAssignmentScreen(assignmentId, navController, assignmentViewModel)
            }
        }
    }
}

@Composable
fun RegisterScreen(x0: AuthViewModel, x1: NavHostController, content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}



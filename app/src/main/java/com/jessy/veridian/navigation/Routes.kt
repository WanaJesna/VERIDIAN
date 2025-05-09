package com.jessy.veridian.navigation


const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_TEACHER = "teacher"
const val ROUT_SPLASH = "splash"
const val ROUT_TRACKER = "tracker"
const val ROUT_CONTACT = "contact"
const val ROUT_HELP = "help"
const val ROUT_SETTING = "setting"
const val ROUT_STUDENT = "student"
const val ROUT_ROLE = "role"
const val ROUT_STUDYMATERIAL= "materials"


//Authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"




//Products

const val ROUT_ADD_ASSIGNMENT = "add_assignment"
const val ROUT_ASSIGNMENT_LIST = "assignment_list"
const val ROUT_EDIT_ASSIGNMENT = "edit_assignment/{assignmentId}"

// ✅ Helper function for navigation
fun editAssignmentRoute(assignmentId: Int) = "edit_assignment/$assignmentId"


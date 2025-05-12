package com.jessy.veridian.repository

import android.content.Context
import com.jessy.veridian.data.AssignmentDatabase
import com.jessy.veridian.model.Assignment

class AssignmentRepository(context: Context) {
    private val assignmentDao = AssignmentDatabase.getDatabase(context).assignmentDao()

    suspend fun insertAssignment(assignment: Assignment) {
        assignmentDao.insertAssignment(assignment)
    }

    fun getAllAssignments() = assignmentDao.getAllAssignments()

    suspend fun deleteAssignment( assignment: Assignment) = assignmentDao.deleteAssignment(assignment)
}
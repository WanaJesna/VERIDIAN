package com.jessy.veridian.repository

import android.content.Context
import com.jessy.veridian.data.AssignmentDatabase
import com.jessy.veridian.model.Assignment
import com.jessy.veridian.database.entities.Assignment

class ProductRepository(context: Context) {
    private val assignmentDao = AssignmentDatabase.getDatabase(context).assignmentDao()

    suspend fun insertProduct(product: Assignment) {
        assignmentDao.insertProduct(product)
    }

    fun getAllProducts() = assignmentDao.getAllAssignment()

    suspend fun deleteAssignment( assignment: Assignment) = assignmentDao.deleteAssignment(assignment)
}
package com.jessy.veridian.database.dao

import androidx.room.*
import com.jessy.veridian.database.entities.Assignment

@Dao
interface AssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Query("SELECT * FROM assignments WHERE teacherId = :teacherId")
    suspend fun getAssignmentsByTeacher(teacherId: Int): List<Assignment>
}
package com.jessy.veridian.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jessy.veridian.model.Assignment

@Dao
interface AssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Query("SELECT * FROM assignments")
    fun getAllAssignments(): LiveData<List<Assignment>>



    @Update
    suspend fun updateAssignment(assignment: Assignment)

    @Delete
    suspend fun deleteAssignment(assignment: Assignment)
}

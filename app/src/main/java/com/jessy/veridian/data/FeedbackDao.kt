package com.jessy.veridian.database.dao

import androidx.room.*
import com.jessy.veridian.database.entities.Feedback

@Dao
interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedback(feedback: Feedback)

    @Query("SELECT * FROM feedback WHERE studentId = :studentId")
    suspend fun getFeedbackByStudent(studentId: Int): List<Feedback>
}
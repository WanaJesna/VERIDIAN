package com.jessy.veridian.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedback")
data class Feedback(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val studentId: Int, // Foreign key to User table
    val message: String
)


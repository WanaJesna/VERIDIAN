package com.jessy.veridian.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignments")
data class Assignment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val teacherId: String, // Foreign key to User table
    val imagePath: String
)


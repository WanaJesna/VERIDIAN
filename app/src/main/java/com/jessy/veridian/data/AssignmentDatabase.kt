package com.jessy.veridian.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jessy.veridian.database.dao.AssignmentDao
import com.jessy.veridian.database.entities.Assignment

@Database(entities = [Assignment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assignmentDao(): AssignmentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "veridian_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
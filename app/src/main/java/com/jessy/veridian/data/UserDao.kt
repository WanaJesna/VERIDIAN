package com.jessy.veridian.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jessy.veridian.model.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): User?
}
package com.example.movieandme2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieandme2.data.local.models.UserLocal

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM Userlocal")
    fun getAll(): List<UserLocal>



    @Query("SELECT * FROM Userlocal WHERE email LIKE :email LIMIT 1")
    fun findByName(email: String): UserLocal?

    @Insert
    fun insert(user: UserLocal)

    @Delete
    fun delete(user: UserLocal)
}
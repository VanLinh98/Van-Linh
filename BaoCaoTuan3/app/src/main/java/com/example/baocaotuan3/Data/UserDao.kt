package com.example.sheek2.Data

import android.service.autofill.UserData
import androidx.room.*


@Dao
interface UsersDao {

    @Query("SELECT * FROM User")
    fun getUsers(): List<dataUser>

    @Insert
    fun insertUser(user: dataUser)

    @Update
    fun updateUser(user: dataUser)

    @Delete
    fun deleteUser(user: dataUser)

    @Query("Select * from User where User.username == :name and User.password == :pass ")
    fun isDangNhap(name: String, pass: String) : dataUser
}
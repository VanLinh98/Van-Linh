package com.example.sheek2.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class dataUser (@PrimaryKey var username: String,
                         @ColumnInfo(name = "password") var password: String?,
                         @ColumnInfo(name = "email") var email: String?,
                         @ColumnInfo(name = "address") var address: String?,
                         @ColumnInfo(name = "gender") var gender: Boolean?)
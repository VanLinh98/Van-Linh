package com.example.quanlynhansu.DATA

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NhanVien")
data class dataNhanVien ( @PrimaryKey var id: String,
                          @ColumnInfo(name = "name") var name: String?,
                          @ColumnInfo(name = "gender") var gender: Boolean?)



package com.example.quanlynhansu.DATA

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NhanVienDao {
    @Insert
    fun insert(nhanvien: dataNhanVien)
    @Query("SELECT * FROM NhanVien")
    fun getAllChapter(): List<dataNhanVien>
    @Delete
    fun delete(nhanvien: dataNhanVien)
    @Query ("DELETE FROM NhanVien")
    fun deleteall()
}
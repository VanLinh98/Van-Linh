package com.example.quanlynhansu.DATA

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(dataNhanVien::class), version = 1)
abstract class NhanVienDatabase : RoomDatabase() {
    abstract fun nhanvienDao(): NhanVienDao
    companion object {
        private var INSTANCE: NhanVienDatabase? = null
        fun getDatabase(context: Context): NhanVienDatabase? {
            if (INSTANCE == null) {
                synchronized(NhanVienDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NhanVienDatabase::class.java, "chapter.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
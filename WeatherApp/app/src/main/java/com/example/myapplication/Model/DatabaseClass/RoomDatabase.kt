package com.example.myapplication.Model.DatabaseClass

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Model.DataClass.CityModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CityModel::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase? {
            return INSTANCE
                    ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "city_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
/*Cho phép Room tạo lại các bảng cơ sở dữ liệu
một cách triệt để nếu Migrationkhông tìm thấy các
lược đồ cơ sở dữ liệu cũ sang phiên bản lược đồ mới nhất.*/
                INSTANCE = instance
                instance
            }
        }
    }
}
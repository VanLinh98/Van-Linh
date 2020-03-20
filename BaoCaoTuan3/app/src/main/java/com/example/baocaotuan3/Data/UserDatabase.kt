package com.example.sheek2.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(dataUser::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userdao() : UsersDao
    companion object {
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        UserDatabase::class.java, "user.db"
                    ).build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
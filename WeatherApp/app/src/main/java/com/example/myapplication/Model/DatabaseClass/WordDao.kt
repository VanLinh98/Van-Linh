package com.example.myapplication.Model.DatabaseClass

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.Model.DataClass.CityModel

@Dao
interface WordDao {

    @Query("SELECT * FROM city_table LIMIT 10")
    fun getDataCity(): LiveData<List<CityModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(model: CityModel)

    @Query("SELECT count(*) FROM city_table ")
    fun countRow() : Int

    @Delete
    fun delete (model: CityModel)

    @Query("SELECT * FROM city_table LIMIT 1")
    fun getcity() : CityModel

    @Query("SELECT * FROM city_table WHERE city LIKE :cityModel ")
    fun GetCityModel(cityModel: String) : CityModel
}
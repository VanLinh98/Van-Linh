package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.DataClass.CityModel
import com.example.myapplication.Model.DataClass.WeatherModel
import com.example.myapplication.Model.DatabaseClass.WordRoomDatabase
import com.example.myapplication.Model.Repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherView(application: Application) : AndroidViewModel(application) {

    private val repository: WeatherRepository
    val allCity: LiveData<List<CityModel>>
    val getone : CityModel
    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope)!!.wordDao()
        repository = WeatherRepository(wordsDao)
        allCity = repository.allCity
        getone = repository.getcity
    }
    fun insert(model: CityModel) = viewModelScope.launch {
        repository.insert(model)
    }
    fun countRow() : Int
    {
        return repository.countRow()
    }
    fun delete(model: CityModel)
    {
        repository.delete(model)
    }
    fun getCityModel(cityModel: String) : Boolean
    {
        return repository.getCityModel(cityModel)
    }
    fun getSearch(query: String) : ArrayList<CityModel>
    {
        return repository.getCity(query)
    }
    fun getDeatail(query: String) : WeatherModel
    {
        return repository.getWeather(query)
    }

}
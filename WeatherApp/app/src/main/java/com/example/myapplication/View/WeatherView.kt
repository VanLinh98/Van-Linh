package com.example.myapplication.View

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Model.CityModel
import com.example.myapplication.Model.WeatherModel
import com.example.myapplication.Database.WordRoomDatabase
import com.example.myapplication.Repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<CityModel>>
    val getone : CityModel
    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository =
            WordRepository(wordsDao)
        allWords = repository.allWords
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
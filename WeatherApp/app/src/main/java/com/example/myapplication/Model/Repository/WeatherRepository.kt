package com.example.myapplication.Model.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.Model.DataClass.CityModel
import com.example.myapplication.Model.DataClass.WeatherModel
import com.example.myapplication.Model.DatabaseClass.WordDao
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL


class WeatherRepository(private val wordDao: WordDao) {

    val allCity: LiveData<List<CityModel>> = wordDao.getDataCity()
    val getcity : CityModel = wordDao.getcity()

    suspend fun insert(model: CityModel) {
        wordDao.insert(model)
    }
    fun delete(model: CityModel){
        wordDao.delete(model)
    }
    fun countRow() : Int
    {
        var count: Int = wordDao.countRow()
        return count
    }
    fun getCityModel(cityModel : String) : Boolean
    {
       val city = wordDao.GetCityModel(cityModel)
        if (city == null)
            return true
        else
            return false
    }
    suspend fun getResponse(query: String) : String
    {
        val response = getUrl(query)
        return response
    }
    fun getCity(query: String) = runBlocking {
        val arrayList: Deferred<ArrayList<CityModel>> = async {
                JsonCity(query)
        }
            return@runBlocking arrayList.await()

    }

    fun getWeather(query: String) = runBlocking {
        val arrayList: Deferred<WeatherModel> = async { JsonWeather(query)  }
        return@runBlocking arrayList.await()
    }

    suspend fun getUrl(url: String)= withContext(Dispatchers.IO){
        val content = StringBuilder()
        try {
            val url = URL(url)
            val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String? = ""
            while ({ line = bufferedReader.readLine(); line }() != null) {
                content.append(line)
            }

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext content.toString()
    }

    suspend fun JsonCity(json : String) : ArrayList<CityModel>
    {
        val a : String = getResponse(json)
        var arraylist = ArrayList<CityModel>()
            try {
                val json = JSONObject(a)
                val jsonObject = json.getJSONObject("search_api")
                val jsonArray = jsonObject.getJSONArray("result")
                for (i in 0..jsonArray.length() - 1) {
                    val JSONObject = jsonArray.getJSONObject(i)
                    val JSONArrayarea = JSONObject.getJSONArray("areaName")
                    for (j in 0..JSONArrayarea.length() - 1) {
                        val JSONObjectvalue = JSONArrayarea.getJSONObject(j)
                        var value: String = JSONObjectvalue.getString("value")
                        arraylist.add(CityModel(value))
                    }
                }
            } catch (e: IOException) {
                    e.printStackTrace()
                }
        return  arraylist

    }

    suspend fun JsonWeather(json: String) : WeatherModel
    {
        lateinit var weather : WeatherModel
        val a : String = getResponse(json)
        try
        {
            val json = JSONObject(a)
            val jsonObject = json.getJSONObject("data")
            val jsonArray = jsonObject.getJSONArray("current_condition")
            for (i in 0..jsonArray.length()-1)
            {
                val JSONObject = jsonArray.getJSONObject(i)
                val temC = JSONObject.getString("temp_C")
                val weatherDesc = JSONObject.getJSONArray("weatherDesc").getJSONObject(0).getString("value")
                val hudimy = JSONObject.getString("humidity")
                val weatherIconUrl = JSONObject.getJSONArray("weatherIconUrl").getJSONObject(0).getString("value")

                weather = WeatherModel(temC, weatherDesc, hudimy, weatherIconUrl)
            }
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        return weather
    }
}
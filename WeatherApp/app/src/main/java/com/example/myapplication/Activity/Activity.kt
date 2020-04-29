package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Model.CityModel
import com.example.myapplication.R
import com.example.myapplication.View.WeatherView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_layout.*

class Activity() : AppCompatActivity() {
    
    lateinit var query : String
    lateinit var cityModel: CityModel
    private lateinit var wordViewModel: WeatherView
    val url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?format=json&key=e2093a0d363d40d7a4982453202704&query="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_layout)

        val Intent = getIntent()
        cityModel  = Intent.getParcelableExtra<CityModel>("history")
        query = cityModel.City.toString()
        wordViewModel = ViewModelProvider(this).get(WeatherView::class.java)
        History()
        Search()
    }

    fun History()
    {
        city.setText(cityModel.City.toString())
        wordViewModel.delete(cityModel)
        wordViewModel.insert(cityModel)
        val WeatherHistory = wordViewModel.getDeatail(url + query+"")
        temp_C.setText(WeatherHistory.tempC.toString() + "°C")
        weatherDesc.setText(WeatherHistory.weatherDesc.toString())
        Picasso.get().load(WeatherHistory.weatherIconUrl.toString()).into(image)
        humidity.setText(WeatherHistory.humidity + "%")
    }

    fun Search()
    {
        city.setText(cityModel.City.toString())
        var count : Int = wordViewModel.countRow()
        if (count<10)
        {
            wordViewModel.insert(cityModel)
        }
        else
        {
            val isValid = wordViewModel.getCityModel(cityModel.City.toString())
            if (isValid == false){
                wordViewModel.delete(cityModel)
                wordViewModel.insert(cityModel)
            }else {
                wordViewModel.delete(wordViewModel.getone)
                wordViewModel.insert(cityModel)
            }
        }
        val WeatherSearch = wordViewModel.getDeatail(url +query+"")
        temp_C.setText(WeatherSearch.tempC.toString() + "°C")
        weatherDesc.setText(WeatherSearch.weatherDesc.toString())
        Picasso.get().load(WeatherSearch.weatherIconUrl.toString()).into(image)
        humidity.setText(WeatherSearch.humidity + "%")
    }
}

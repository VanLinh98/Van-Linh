package com.example.myapplication.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Model.DataClass.CityModel
import com.example.myapplication.R
import com.example.myapplication.ViewModel.WeatherView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_layout.*

class Activity() : AppCompatActivity() {

    lateinit var cityWeather : String
    lateinit var cityModel: CityModel
    private lateinit var wordViewModel: WeatherView
    val url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?format=json&key=e2093a0d363d40d7a4982453202704&query="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_layout)

        val Intent = getIntent()
        cityModel  = Intent.getParcelableExtra("city")
        cityWeather = cityModel.City
        wordViewModel = ViewModelProvider(this).get(WeatherView::class.java)
        Weather()
        History()
    }
    fun History()
    {
        city.setText(cityModel.City)
        wordViewModel.delete(cityModel)
        wordViewModel.insert(cityModel)
        val DetailHistory = wordViewModel.getDeatail(url +cityWeather+"")
        temp_C.setText(DetailHistory.tempC)
        weatherDesc.setText(DetailHistory.weatherDesc)
        Picasso.get().load(DetailHistory.weatherIconUrl).into(image)
        humidity.setText(DetailHistory.humidity)
    }

    fun Weather()
    {
        city.setText(cityModel.City)
        var count : Int = wordViewModel.countRow()
        if (count<10)
        {
            wordViewModel.insert(cityModel)
        }
        else
        {
            val cityData = wordViewModel.getCityModel(cityModel.City)
            if (cityData == false){
                wordViewModel.delete(cityModel)
                wordViewModel.insert(cityModel)
            }else {
                wordViewModel.delete(wordViewModel.getone)
                wordViewModel.insert(cityModel)
            }
        }
        val WeatherSearch = wordViewModel.getDeatail(url +cityWeather+"")
        temp_C.setText(WeatherSearch.tempC + "°C")
        weatherDesc.setText(WeatherSearch.weatherDesc)
        Picasso.get().load(WeatherSearch.weatherIconUrl).into(image)
        humidity.setText(WeatherSearch.humidity + "%")
    }
}

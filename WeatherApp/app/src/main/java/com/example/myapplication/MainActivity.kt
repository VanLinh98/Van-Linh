package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.AdapterHistory
import com.example.myapplication.Model.CityModel
import com.example.myapplication.View.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    lateinit var wordViewModel: WordViewModel
    var arraylist = ArrayList<CityModel>()
    val adapter = AdapterHistory(arraylist)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_history.adapter = adapter
        rv_history.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_history.setLayoutManager(LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false))

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer<List<CityModel>> {
            val list : MutableList<CityModel> = mutableListOf()
            for (i in 0..it.size-1)
            {
                list.add(0,it.get(i))
            }
            adapter.setWords(list)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_layout,menu)
        val searchView : SearchView = menu!!.findItem(R.id.MenuSearch).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                tvhistory.visibility = View.GONE
                arraylist = wordViewModel.getSearch("http://api.worldweatheronline.com/premium/v1/search.ashx?format=json&key=e2093a0d363d40d7a4982453202704&query="+query+"")
                adapter.setWords(arraylist)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                tvhistory.visibility = View.GONE
                wordViewModel = ViewModelProvider(this@MainActivity).get(WordViewModel::class.java)
                wordViewModel.allWords.observe(this@MainActivity, Observer<List<CityModel>> {
                    val citysearch : MutableList<CityModel> = mutableListOf()
                    val cityhistory : MutableList<CityModel> = mutableListOf()
                    for (i in 0..it.size -1)
                    {
                        citysearch.add(0,it.get(i))
                    }
                    for (i in 0 .. citysearch.size -1)
                    {
                        if (citysearch[i].toString().contains(newText))
                            cityhistory.add(0,citysearch.get(i))
                    }
                    adapter.setWords(cityhistory)
                    adapter.notifyDataSetChanged()
                })
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        tvhistory.visibility=View.VISIBLE
        rv_history.adapter = adapter
        rv_history.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_history.setLayoutManager(LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false))

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer<List<CityModel>> {
            val list : MutableList<CityModel> = mutableListOf()
            for (i in 0..it.size-1)
            {
                list.add(0,it.get(i))
            }
            adapter.setWords(list)
            adapter.notifyDataSetChanged()
        })

        super.onRestart()
    }
}

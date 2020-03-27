package com.example.baocaotuan3.View

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.baocaotuan3.Model.Messages
import com.example.baocaotuan3.Respository.MessageRespository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.IOException
import kotlin.system.measureTimeMillis


class MessageView(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRespository

    init {
        repository = MessageRespository()
    }

    suspend fun insert(s: String) : ArrayList<Messages>   {
        val a : String  = repository.fetchDocs(s) //thực hiện network reques
        val arrayList = ArrayList<Messages>()
        try {
            val json = JSONObject(a)
            val jsonArray = json.getJSONArray("messages")
            for (i in 0..jsonArray.length() - 1) {
                val JSONObject = jsonArray.getJSONObject(i)
                var id: String = JSONObject.getString("id")
                var from: String = JSONObject.getString("from")
                var email: String = JSONObject.getString("email")
                var subject: String = JSONObject.getString("subject")
                var message: String = JSONObject.getString("message")
                var date: String = JSONObject.getString("date")
                arrayList.add(Messages(id, from, email, subject, message, date))
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return arrayList
    }

    fun main(s : String)  = runBlocking {
        val time = measureTimeMillis {
            val arrayList: Deferred<ArrayList<Messages>> = async { insert(s)  }
            return@runBlocking arrayList.await()
        }
    }
}
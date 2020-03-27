package com.example.baocaotuan3.Respository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL

class MessageRespository {

    //thực hiện network reques/chạy như coroutine
    suspend fun fetchDocs(s : String): String {
        val result  = get(s)
        Log.d("AAA",result)
        return result
    }
    suspend fun get(url: String) = withContext(Dispatchers.IO){
        val content = StringBuilder()
        try {
            val url = URL(url)
            val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String? = ""
            while ({ line = bufferedReader.readLine(); line }() != null) {
                content.append(line) //nỗi chuỗi vào content
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext content.toString()
    }
}
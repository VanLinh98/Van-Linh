package com.example.quanlynhansu

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.example.quanlynhansu.Adapter.NhanVien
import com.example.quanlynhansu.Adapter.NhanVienAdapter
import com.example.quanlynhansu.DATA.NhanVienDatabase
import com.example.quanlynhansu.DATA.dataNhanVien
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var nvDatabase: NhanVienDatabase? = null
    var arraynv : ArrayList<NhanVien> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btndelete.setOnClickListener{ it: View? ->
//            main1()
            xuLyXoa()
        }

        btnsave.setOnClickListener {
            save()

        }
    }

    fun xuLyXoa() {
            for (i in lvnhanvien.childCount-1 downTo 0){
                var chk : CheckBox = lvnhanvien.getChildAt(i).findViewById(R.id.chkbox)
                var xoaid = arraynv.get(i).id.toString()
                var xoaname = arraynv.get(i).name.toString()
                var xoagender = arraynv.get(i).gender
                if (chk.isChecked){
                    arraynv.removeAt(i)
                    val nvObj = dataNhanVien(xoaid,xoaname,xoagender)
                    Delete(this,nvObj).execute()
                }
            }
        lvnhanvien.adapter = NhanVienAdapter(this,  arraynv)
        GetDataFromDb(this@MainActivity).execute()

    }

    fun save() {
        var ma = editMa.text.toString().trim()
        var ten = editTen.text.toString().trim()
        var gioitinh = false
        if(radioGioitinh.checkedRadioButtonId == R.id.radNu){
            gioitinh = true
        }
        nvDatabase = NhanVienDatabase.getDatabase(this)
        val nvObj = dataNhanVien(ma,ten,gioitinh)
        InsertTask(this,nvObj).execute()
        GetDataFromDb(this@MainActivity).execute()

        editMa.setText("")
        editTen.setText("")
        editMa.requestFocus()

    }

    inner class InsertTask(var context: MainActivity, var nv: dataNhanVien) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.nvDatabase!!.nhanvienDao().insert(nv)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class GetDataFromDb(var context: MainActivity) : AsyncTask<Void, Void, List<dataNhanVien>>() {
        override fun doInBackground(vararg params: Void?): List<dataNhanVien> {
            return context.nvDatabase!!.nhanvienDao().getAllChapter()
        }
        override fun onPostExecute(chapterList: List<dataNhanVien>?) {
            if (chapterList!!.size > 0) {
                for (i in chapterList.size-1 downTo 0 ) {
                    var nv : NhanVien = NhanVien(chapterList[i].id, chapterList[i].name.toString(), chapterList[i].gender)
                    arraynv.add(nv)
                    lvnhanvien.adapter = NhanVienAdapter(this@MainActivity,arraynv)
                }
            }
        }
    }

    inner class Delete(var context: MainActivity, var nv: dataNhanVien) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.nvDatabase!!.nhanvienDao().delete(nv)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(context, "Delete success", Toast.LENGTH_LONG).show()
            }
        }
    }

//        fun foo(): Flow<Int> = flow {
//        save()
//    }
//    fun xoaUp(): Flow<Int> = flow {
//        xulyXoa()
//    }
//
//    fun main() = runBlocking {
//        launch {
//            GetDataFromDb(this@MainActivity).execute()
//            delay(50)
//        }
//        foo().collect {  }
//    }
//
//    fun main1() = runBlocking {
//
//        launch {
//            GetDataFromDb(this@MainActivity).execute()
//            delay(50)
//        }
//        xoaUp().collect {  }
//
//    }





//        fun hienthi(){
//        var ma = editMa.text.toString().trim()
//        var ten = editTen.text.toString().trim()
//        var gioitinh = false
//        if(radioGioitinh.checkedRadioButtonId == R.id.radNu){
//            gioitinh = true
//        }
//        var nv = NhanVien(ma,ten,gioitinh)
//        arraynv.add(nv)
//        lvnhanvien.adapter = NhanVienAdapter(this,arraynv)
//        editMa.setText("")
//        editTen.setText("")
//        editMa.requestFocus()
//    }


//    fun main() = runBlocking{
//        launch {
//            val flow = foo()
//            flow.collect { }
//        }
//        launch {
//            hienthi()
//        }
//    }
}


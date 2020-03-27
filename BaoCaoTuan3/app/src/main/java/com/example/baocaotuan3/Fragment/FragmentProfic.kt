package com.example.baocaotuan3.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baocaotuan3.Model.Profic
import com.example.baocaotuan3.R
import kotlinx.android.synthetic.main.item_profile.*


class Fragmentprofic(private var profic: Profic) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameprofic.text = profic.name
        emailprofic.text = profic.email
        addressprofic.text = profic.address
        if (profic.gender == true){
            gioitinhprofic.text = "Nữ"
        }else{
            gioitinhprofic.text = "Nam"
        }
    }

}
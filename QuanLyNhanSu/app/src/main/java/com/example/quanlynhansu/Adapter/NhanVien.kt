package com.example.quanlynhansu.Adapter

class NhanVien {
    var id : String
    var name : String
    var gender : Boolean

    constructor(id: String, name: String, gender: Boolean?) {
        this.id = id
        this.name = name
        this.gender = gender!!
    }
}
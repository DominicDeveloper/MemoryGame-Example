package com.asadbek.memorygame2023

import java.io.Serializable

class Record:Serializable {
    var record:String? = "0"

    constructor(record: String?){
        this.record = record
    }
    constructor()
}
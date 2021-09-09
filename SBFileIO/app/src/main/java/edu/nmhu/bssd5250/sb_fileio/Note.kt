package edu.nmhu.bssd5250.sb_fileio

import java.util.*

class Note(var name:String, var desc:String, var date: String?) {

    init{
        if (date == null) {
            date = Date().toString()
        }
    }

    override fun toString(): String {
        return "$name, $date, $desc"
    }
}
package com.example.acalculator

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class OperationUi (val expressao: String, val resultado: String) {

    @RequiresApi(Build.VERSION_CODES.O)
    var timestamp : Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()

    override fun toString(): String {
        return "$expressao=$resultado - ms:$timestamp"
    }

    fun data (): String {
        var formatter = SimpleDateFormat("dd/MM/yyyy - hh:mm")
        var calender = Calendar.getInstance()
        calender.timeInMillis = this.timestamp
        return formatter.format(calender.time)
    }
}
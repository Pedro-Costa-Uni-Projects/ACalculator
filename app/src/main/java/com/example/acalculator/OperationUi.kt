package com.example.acalculator

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

@Parcelize
class OperationUi (val expressao: String, val resultado: String) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.O)
    var id = ZoneId.of("Europe/London").getRules().getOffset(Instant.now())

    @RequiresApi(Build.VERSION_CODES.O)
    var timestamp : Long = LocalDateTime.now().toInstant(ZoneOffset.of(id.toString())).toEpochMilli()

    override fun toString(): String {
        return "$expressao=$resultado - ms:$timestamp"
    }

    fun data (): String {
        var formatter = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss")
        var calender = Calendar.getInstance()
        calender.timeInMillis = this.timestamp
        return formatter.format(calender.time)
    }
}
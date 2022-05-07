package com.example.acalculator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

class Calculator(private val dao: OperationDao) {
    var display: String = "0"

    fun insertSymbolNumber(symbol: String) : String {
        if(display.last() == '0' && display.length < 2) {
            display = symbol
        } else {
            display += symbol
        }
        return display
    }

    fun insertSymbolOperation(symbol: String) : String {
        if(display.last() !in arrayOf('/','%','*','-','+','.')) {
            display += symbol
        }
        return  display
    }

    fun insertSymbolDelete(symbol: String) : String {
        if (symbol == "C") {
            display = "0"
        } else if (symbol == "<"){
            if(display.length != 1 && display[0] != '0') {
                display = display.substring(0, display.length-1)
            } else {
                display = "0"
            }
        }
        return display
    }

    fun performOperation(onFinished: () -> Unit) : String {
        val valorFinal : String
        val expression = ExpressionBuilder(display).build()
        val expressao = expression.evaluate()
        valorFinal = if(expressao % 1 == 0.0) {
            expressao.toInt().toString()
        } else {
            expressao.toString()
        }

        display = valorFinal

        val operation = OperationRoom(
            expression = display, result = valorFinal, timestamp = Date().time
        )
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(operation)
            onFinished()
        }
        return valorFinal
    }

    /* estava a dar erro, não sei se é para eliminar
    private fun addToHistory(expression: String, result: String) {
        history.add(Operation(expression, result))
    }
    */

    fun getAllOperations(onFinished: (List<OperationUi>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val operations = dao.getAll()
            onFinished(operations.map {
                OperationUi(it.uuid, it.expression, it.result, it.timestamp)
            })
        }
    }
}
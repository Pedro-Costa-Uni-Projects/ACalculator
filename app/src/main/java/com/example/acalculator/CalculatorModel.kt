package com.example.acalculator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorModel {
    var display: String = "0"
    private val history = mutableListOf<Operation>()

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

    fun performOperation() : String {
        val valorFinal : String
        val expression = ExpressionBuilder(display).build()
        val expressao = expression.evaluate()
        valorFinal = if(expressao % 1 == 0.0) {
            expressao.toInt().toString()
        } else {
            expressao.toString()
        }
        history.add(Operation(display, valorFinal))
        display = valorFinal
        CoroutineScope(Dispatchers.IO).launch {
            addToHistory(display, valorFinal)
        }
        return valorFinal
    }

    private fun addToHistory(expression: String, result: String) {
        history.add(Operation(expression, result))
    }

    fun getAllOperations(callback: (List<Operation>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            callback(history.toList())
        }
    }
}
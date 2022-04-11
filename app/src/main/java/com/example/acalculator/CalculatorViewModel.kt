package com.example.acalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
     val model = CalculatorModel()

    fun getDisplayView () = model.display

    fun onClickSymbolNumber(symbol: String): String {
        return model.insertSymbolNumber(symbol)
    }

    fun onClickSymbolOperation(symbol: String) : String {
        return model.insertSymbolOperation(symbol)
    }

    fun onClickSymbolDelete(symbol: String) : String {
        return model.insertSymbolDelete(symbol)
    }

    fun onClickEquals(): String {
        return model.performOperation()
    }

    fun getHistory(callback: (List<OperationUi>) -> Unit ) {
        model.getAllOperations { operations ->
            val history = operations.map {
                OperationUi(it.expression, it.result, it.timestamp)
            }
            CoroutineScope(Dispatchers.Main).launch {
                callback(history)
            }
        }
    }
}
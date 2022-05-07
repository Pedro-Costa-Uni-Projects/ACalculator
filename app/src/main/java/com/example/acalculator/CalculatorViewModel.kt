package com.example.acalculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {
     val model = Calculator(
         CalculatorDatabase.getInstance(application).operationDao()
     )

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

    fun onClickEquals(onSaved: () -> Unit): String {
        return model.performOperation(onSaved)
    }

    fun getHistory(callback: (List<OperationUi>) -> Unit ) {
        CoroutineScope(Dispatchers.Main).launch {
            model.getAllOperations {
                callback
            }
        }
    }
}
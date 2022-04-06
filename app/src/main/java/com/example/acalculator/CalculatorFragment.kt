package com.example.acalculator

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acalculator.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

const val CALCULATOR_OPERATIONS = "operations"

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    private val TAG = MainActivity::class.java.simpleName
    private var operations : ArrayList<OperationUi>? = null
    private val adapter = HistoryAdapter(::onOperationClick, ::onOperationLongClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        binding = FragmentCalculatorBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        //buscar da main activity a lista de operações, se não ouver nada antes fica vazia
        operations = activity?.intent?.getParcelableArrayListExtra(CALCULATOR_OPERATIONS)
        if(operations == null) {
            operations = ArrayList()
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.calculator)
        super.onStart()

        //Listeners Buttons
        binding.button00?.setOnClickListener { onClickSymbolNumber("00") }

        binding.button0.setOnClickListener { onClickSymbolNumber("0") }

        binding.button1.setOnClickListener { onClickSymbolNumber("1") }

        binding.button2.setOnClickListener { onClickSymbolNumber("2") }

        binding.button3.setOnClickListener { onClickSymbolNumber("3") }

        binding.button4.setOnClickListener { onClickSymbolNumber("4") }

        binding.button5.setOnClickListener { onClickSymbolNumber("5") }

        binding.button6.setOnClickListener { onClickSymbolNumber("6") }

        binding.button7.setOnClickListener { onClickSymbolNumber("7") }

        binding.button8.setOnClickListener { onClickSymbolNumber("8") }

        binding.button9.setOnClickListener { onClickSymbolNumber("9") }

        binding.buttonDiv.setOnClickListener { onClickSymbolOperation("/") }

        binding.buttonModulo.setOnClickListener { onClickSymbolOperation("%") }

        binding.buttonMulti.setOnClickListener {  onClickSymbolOperation("*")  }

        binding.buttonSub.setOnClickListener { onClickSymbolOperation("-") }

        binding.buttonAddition.setOnClickListener { onClickSymbolOperation("+") }

        binding.buttonDot.setOnClickListener {onClickSymbolOperation(".") }

        binding.buttonEquals.setOnClickListener {onClickEquals()}

        binding.buttonAllDelete.setOnClickListener {onClickSymbolDelete("C") }

        binding.buttonFirstDelete.setOnClickListener { onClickSymbolDelete("<") }
        //Listeners Buttons

        binding.rvHistoric?.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvHistoric?.adapter = adapter

    }

    private fun onClickSymbolNumber(symbol:String) {
        Log.i(TAG, "Cliquei no botão $symbol")
        if(binding.textVisor.text.last() == '0' && binding.textVisor.text.length < 2) {
            binding.textVisor.text = symbol
        } else {
            binding.textVisor.append(symbol)
        }
    }

    private fun onClickSymbolOperation(symbol:String) {
        Log.i(TAG, "Cliquei no botão $symbol")
        if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
            binding.textVisor.append(symbol)
        }
    }

    private fun onClickSymbolDelete(symbol:String) {
        Log.i(TAG, "Cliquei no botão $symbol")
        if (symbol == "C") {
            binding.textVisor.text = "0"
        } else if (symbol == "<"){
            if(binding.textVisor.text.length != 1 && binding.textVisor.text[0] != '0') {
                binding.textVisor.text = binding.textVisor.text.substring(0, binding.textVisor.text.length-1)
            } else {
                binding.textVisor.text = "0"
            }
        }
    }

    private fun onClickEquals() {
        Log.i(TAG, "Cliquei no botão =")
        val valorFinal : String
        val expression = ExpressionBuilder(
            binding.textVisor.text.toString()
        ).build()
        val expressao = expression.evaluate()
        valorFinal = if(expressao % 1 == 0.0) {
            expressao.toInt().toString()
        } else {
            expressao.toString()
        }
        operations?.add(OperationUi(binding.textVisor.text.toString(), valorFinal))
        binding.textVisor.text = valorFinal
        operations?.let { adapter.updateItems(it) }
        Log.i(TAG, "O resultado da expressão é ${binding.textVisor.text}")
        //passar para a main activity a lista de operações
        activity?.intent?.putParcelableArrayListExtra(CALCULATOR_OPERATIONS, operations)
    }

    private fun onOperationClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.toString(), Toast.LENGTH_LONG).show()
    }

    private fun onOperationLongClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.data(), Toast.LENGTH_LONG).show()
    }

}
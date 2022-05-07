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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acalculator.databinding.FragmentCalculatorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*
import kotlin.collections.ArrayList

const val CALCULATOR_OPERATIONS = "operations"

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    lateinit var viewModel: CalculatorViewModel
    private val TAG = MainActivity::class.java.simpleName
    private var operations : ArrayList<OperationUi>? = null
    private val adapter = HistoryAdapter(::onOperationClick, ::onOperationLongClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        binding = FragmentCalculatorBinding.bind(view)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        binding.textVisor.text = viewModel.getDisplayView()
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
        binding.button00?.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("00")
        }

        binding.button0.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("0")
        }

        binding.button1.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("1")
        }

        binding.button2.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("2")
        }

        binding.button3.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("3")
        }

        binding.button4.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("4")
        }

        binding.button5.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("5")
        }

        binding.button6.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("6")
        }

        binding.button7.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("7")
        }

        binding.button8.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("8")
        }

        binding.button9.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolNumber("9")
        }

        binding.buttonDiv.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation("/")
        }

        binding.buttonModulo.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation("%")
        }

        binding.buttonMulti.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation("*")
        }

        binding.buttonSub.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation("-")
        }

        binding.buttonAddition.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation("+")
        }

        binding.buttonDot.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolOperation(".")
        }

        binding.buttonEquals.setOnClickListener {
            // A melhorar na proxima aula 7
            val temp = binding.textVisor.text
            // A melhorar na proxima aula 7

            binding.textVisor.text = viewModel.onClickEquals{
                CoroutineScope(Dispatchers.Main).launch {
                    val toInsert = OperationUi(UUID.randomUUID().toString(),temp as String, binding.textVisor.text as String, Date().time)
                    operations!!.add(toInsert)
                    operations?.let { adapter.updateItems(it) }
                    activity?.intent?.putParcelableArrayListExtra(CALCULATOR_OPERATIONS, operations)
                }
            }

        }

        binding.buttonAllDelete.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolDelete("C")
        }

        binding.buttonFirstDelete.setOnClickListener {
            binding.textVisor.text = viewModel.onClickSymbolDelete("<")
        }
        //Listeners Buttons

        binding.rvHistoric?.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvHistoric?.adapter = adapter

    }

    private fun onOperationClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.toString(), Toast.LENGTH_LONG).show()
    }

    private fun onOperationLongClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.data(), Toast.LENGTH_LONG).show()
    }

}
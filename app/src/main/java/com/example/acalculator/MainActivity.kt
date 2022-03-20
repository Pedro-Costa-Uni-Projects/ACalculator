package com.example.acalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.acalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.button0.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 0")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "0"
            } else {
                binding.textVisor.append("0")
            }
        }

        binding.button1.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 1")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "1"
            } else {
                binding.textVisor.append("1")
            }
        }

        binding.button2.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 2")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "2"
            } else {
                binding.textVisor.append("2")
            }
        }

        binding.button3.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 3")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "3"
            } else {
                binding.textVisor.append("3")
            }
        }

        binding.button4.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 4")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "4"
            } else {
                binding.textVisor.append("4")
            }
        }

        binding.button5.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 5")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "5"
            } else {
                binding.textVisor.append("5")
            }
        }

        binding.button6.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 6")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "6"
            } else {
                binding.textVisor.append("6")
            }
        }

        binding.button7.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 7")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "7"
            } else {
                binding.textVisor.append("7")
            }
        }

        binding.button8.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 8")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "8"
            } else {
                binding.textVisor.append("8")
            }
        }

        binding.button9.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 9")
            if(binding.textVisor.text.last() == '0') {
                binding.textVisor.text = "9"
            } else {
                binding.textVisor.append("9")
            }
        }

        binding.buttonDiv.setOnClickListener {
            Log.i(TAG, "Cliquei no botão /")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
                binding.textVisor.append("/")
            }
        }

        binding.buttonModulo.setOnClickListener {
            Log.i(TAG, "Cliquei no botão %")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
                binding.textVisor.append("%")
            }
        }

        binding.buttonMulti.setOnClickListener {
            Log.i(TAG, "Cliquei no botão *")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
                binding.textVisor.append("*")
            }
        }

        binding.buttonSub.setOnClickListener {
            Log.i(TAG, "Cliquei no botão -")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
                binding.textVisor.append("-")
            }
        }

        binding.buttonAddition.setOnClickListener {
            Log.i(TAG, "Cliquei no botão +")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.')) {
                binding.textVisor.append("+")
            }
        }

        binding.buttonDot.setOnClickListener {
            Log.i(TAG, "Cliquei no botão .")
            if(binding.textVisor.text.last() !in arrayOf('/','%','*','-','+','.') && !binding.textVisor.text.contains('.')) {
                binding.textVisor.append(".")
            }
        }

        binding.buttonEquals.setOnClickListener {
            Log.i(TAG, "Cliquei no botão =")
            val expression = ExpressionBuilder(
                binding.textVisor.text.toString()
            ).build()
            binding.textVisor.text = expression.evaluate().toString()
            Log.i(TAG, "O resultado da expressão é ${binding.textVisor.text}")
        }

        binding.buttonAllDelete.setOnClickListener {
            Log.i(TAG, "Cliquei no botão C")
            binding.textVisor.text = "0"
        }

        binding.buttonFirstDelete.setOnClickListener {
            Log.i(TAG, "Cliquei no botão <")
            if(binding.textVisor.text.length != 1 && binding.textVisor.text[0] != '0') {
                binding.textVisor.text = binding.textVisor.text.substring(0, binding.textVisor.text.length-1)
            } else {
                binding.textVisor.text = "0"
            }
        }

    }

}
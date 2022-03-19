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

        binding.button1.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 1")
            if(binding.textVisor.text == "0") {
                binding.textVisor.text = "1"
            } else {
                binding.textVisor.append("1")
            }
        }

        binding.button2.setOnClickListener {
            Log.i(TAG, "Cliquei no botão 2")
            if(binding.textVisor.text == "0") {
                binding.textVisor.text = "2"
            } else {
                binding.textVisor.append("2")
            }
        }

        binding.buttonAddition.setOnClickListener {
            Log.i(TAG, "Cliquei no botão +")
            binding.textVisor.append("+")
        }

        binding.buttonEquals.setOnClickListener {
            Log.i(TAG, "Cliquei no botão =")
            val expression = ExpressionBuilder(
                binding.textVisor.text.toString()
            ).build()
            binding.textVisor.text = expression.evaluate().toString()
            Log.i(TAG, "O resultado da expressão é ${binding.textVisor.text}")
        }


    }



}
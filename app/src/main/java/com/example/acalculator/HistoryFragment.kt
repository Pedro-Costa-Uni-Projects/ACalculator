package com.example.acalculator

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acalculator.databinding.FragmentHistoryBinding
import java.util.ArrayList

private const val ARG_OPERATIONS = "operations"

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private var operations: ArrayList<OperationUi>? = null
    private val adapter = HistoryAdapter(::onOperationClick, ::onOperationLongClick)

    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onPause() {
        super.onPause()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            operations = it.getParcelableArrayList(ARG_OPERATIONS)
        }
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Histórico"
        binding.rvHistoricFragment.layoutManager = LinearLayoutManager(activity as Context)
        operations?.let { adapter.updateItems(it) }
        binding.rvHistoricFragment.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        binding = FragmentHistoryBinding.bind(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(operations: ArrayList<OperationUi>?) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_OPERATIONS, operations)
                }
            }
    }

    private fun onOperationClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.toString(), Toast.LENGTH_LONG).show()
    }

    private fun onOperationLongClick(operation: OperationUi) {
        Toast.makeText(activity as Context, operation.data(), Toast.LENGTH_LONG).show()
    }
}
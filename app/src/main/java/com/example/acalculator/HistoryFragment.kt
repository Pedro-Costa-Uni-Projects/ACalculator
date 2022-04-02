package com.example.acalculator

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.acalculator.databinding.FragmentHistoryBinding
import java.util.ArrayList

private const val ARG_OPERATIONS = "operations"

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private var operations: ArrayList<OperationUi>? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        binding = FragmentHistoryBinding.bind(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_OPERATIONS, operations)
                }
            }
    }
}
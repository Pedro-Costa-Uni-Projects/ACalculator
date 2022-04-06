package com.example.acalculator

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.acalculator.databinding.FragmentOperationDetailBinding

private const val ARG_OPERATION = "operation"

class OperationDetailFragment : Fragment() {
    private lateinit var binding: FragmentOperationDetailBinding
    private var operation: OperationUi? = null

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
            operation = it.getParcelable(ARG_OPERATION)
        }
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.details)
        binding.expressao.text = getString(R.string.expressao, operation?.expressao)
        binding.resultado.text = getString(R.string.resultado, operation?.resultado)
        binding.data.text = getString(R.string.data, operation?.data())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operation_detail, container, false)
        binding = FragmentOperationDetailBinding.bind(view)
        return  binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(operation: OperationUi?) =
            OperationDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_OPERATION, operation)
                }
            }
    }
}
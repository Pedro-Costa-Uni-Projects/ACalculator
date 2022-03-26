package com.example.acalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acalculator.databinding.ItemExpressionBinding

class HistoryAdapter(private val onOperationClick: (OperationUi) -> Unit,
                     private val onOperationLongClick: (OperationUi) -> Unit,
                     private var items: List<OperationUi> = listOf()) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder (val binding: ItemExpressionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    HistoryViewHolder {
        return HistoryViewHolder(
            ItemExpressionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onOperationClick(items[position])
        }
        holder.itemView.setOnLongClickListener{
            onOperationLongClick(items[position])
            true
        }
        val operation = items[position]
        holder.binding.textExpression.text = operation.expressao
        holder.binding.textResult.text = operation.resultado
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<OperationUi>) {
        this.items = items
        notifyDataSetChanged()
    }

}
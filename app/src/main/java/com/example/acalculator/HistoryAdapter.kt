package com.example.acalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acalculator.databinding.ItemExpressionBinding

class HistoryAdapter(private var items: List<String> = listOf()) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
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
        val parts = items[position]?.split("=")
        holder.binding.textExpression.text = parts?.get(0)
        holder.binding.textResult.text = parts?.get(1)
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

}
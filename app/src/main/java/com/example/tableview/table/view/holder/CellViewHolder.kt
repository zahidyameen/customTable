package com.example.tableview.table.view.holder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tableview.R
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tableview.table.view.model.Cell

class CellViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    private val cellTextView: TextView = itemView.findViewById(R.id.cell_data)
    private val cellContainer: LinearLayout = itemView.findViewById(R.id.cell_container)

    fun setCell(cell: Cell?) {
        cellTextView.text = cell?.data.toString()

        // Optional: Auto-resize cell layout
        cellContainer.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        cellTextView.requestLayout()
    }
}

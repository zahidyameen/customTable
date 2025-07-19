package com.example.tableview.table.view.holder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tableview.R
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tableview.table.view.ITableView
import com.example.tableview.table.view.model.ColumnHeader

class ColumnHeaderViewHolder(itemView: View, private val tableView: ITableView?) : AbstractViewHolder(itemView) {

    private val columnHeaderContainer: LinearLayout = itemView.findViewById(R.id.column_header_container)
    private val columnHeaderTextView: TextView = itemView.findViewById(R.id.column_header_textView)


    fun setColumnHeader(columnHeader: ColumnHeader?) {
        columnHeaderTextView.text = columnHeader?.data.toString()

        // Auto-resize layout
        columnHeaderContainer.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        columnHeaderTextView.requestLayout()
    }
}

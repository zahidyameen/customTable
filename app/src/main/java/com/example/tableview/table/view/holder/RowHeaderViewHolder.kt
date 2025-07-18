package com.example.tableview.table.view.holder

import android.view.View
import android.widget.TextView
import com.example.tableview.R
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder

class RowHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    val rowHeaderTextView: TextView = itemView.findViewById(R.id.row_header_textview)
}
package com.example.tableview.table.view.holder

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tableview.R
import com.example.tableview.table.adapter.recyclerview.holder.AbstractSorterViewHolder
import com.example.tableview.table.sort.SortState
import com.example.tableview.table.view.ITableView
import com.example.tableview.table.view.model.ColumnHeader

class ColumnHeaderViewHolder(itemView: View, private val tableView: ITableView?) : AbstractSorterViewHolder(itemView) {

    private val columnHeaderContainer: LinearLayout = itemView.findViewById(R.id.column_header_container)
    private val columnHeaderTextView: TextView = itemView.findViewById(R.id.column_header_textView)
    private val columnHeaderSortButton: ImageButton = itemView.findViewById(R.id.column_header_sortButton)

    init {
        columnHeaderSortButton.setOnClickListener { onSortButtonClick() }
    }

    fun setColumnHeader(columnHeader: ColumnHeader?) {
        columnHeaderTextView.text = columnHeader?.data.toString()

        // Auto-resize layout
        columnHeaderContainer.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        columnHeaderTextView.requestLayout()
    }

    private fun onSortButtonClick() {
        when (sortState) {
            SortState.ASCENDING -> tableView?.sortColumn(adapterPosition, SortState.DESCENDING)
            SortState.DESCENDING -> tableView?.sortColumn(adapterPosition, SortState.ASCENDING)
            else -> tableView?.sortColumn(adapterPosition, SortState.DESCENDING)
        }
    }

    override fun onSortingStatusChanged(sortState: SortState) {
        Log.e(
            "ColumnHeaderViewHolder",
            "+ onSortingStatusChanged: x: ${adapterPosition}, old state: $sortState, current state: $sortState, visibility: ${columnHeaderSortButton.visibility}"
        )

        super.onSortingStatusChanged(sortState)

        columnHeaderContainer.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT

        controlSortState(sortState)

        Log.e(
            "ColumnHeaderViewHolder",
            "- onSortingStatusChanged: x: ${adapterPosition}, old state: $sortState, current state: $sortState, visibility: ${columnHeaderSortButton.visibility}"
        )

        columnHeaderTextView.requestLayout()
        columnHeaderSortButton.requestLayout()
        columnHeaderContainer.requestLayout()
        itemView.requestLayout()
    }

    private fun controlSortState(sortState: SortState) {
        when (sortState) {
            SortState.ASCENDING -> {
                columnHeaderSortButton.visibility = View.VISIBLE
                columnHeaderSortButton.setImageResource(R.drawable.ic_down)
            }
            SortState.DESCENDING -> {
                columnHeaderSortButton.visibility = View.VISIBLE
                columnHeaderSortButton.setImageResource(R.drawable.ic_up)
            }
            SortState.UNSORTED -> {
                columnHeaderSortButton.visibility = View.INVISIBLE
            }
        }
    }
}

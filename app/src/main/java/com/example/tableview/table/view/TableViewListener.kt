package com.example.tableview.table.view

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tableview.table.listener.ITableViewListener
import com.example.tableview.table.view.holder.ColumnHeaderViewHolder
import com.example.tableview.table.view.popup.ColumnHeaderLongPressPopup
import com.example.tableview.table.view.popup.RowHeaderLongPressPopup

class TableViewListener(private val tableView: ITableView) : ITableViewListener {

    private val context: Context = tableView.context

    override fun onCellClicked(cellView: RecyclerView.ViewHolder, column: Int, row: Int) {
        showToast("Cell $column $row has been clicked.")
    }

    override fun onCellDoubleClicked(cellView: RecyclerView.ViewHolder, column: Int, row: Int) {
        showToast("Cell $column $row has been double clicked.")
    }

    override fun onCellLongPressed(cellView: RecyclerView.ViewHolder, column: Int, row: Int) {
        showToast("Cell $column $row has been long pressed.")
    }

    override fun onColumnHeaderClicked(columnHeaderView: RecyclerView.ViewHolder, column: Int) {
        showToast("Column header $column has been clicked.")
    }

    override fun onColumnHeaderDoubleClicked(columnHeaderView: RecyclerView.ViewHolder, column: Int) {
        showToast("Column header $column has been double clicked.")
    }

    override fun onColumnHeaderLongPressed(columnHeaderView: RecyclerView.ViewHolder, column: Int) {
        if (columnHeaderView is ColumnHeaderViewHolder) {
            val popup = ColumnHeaderLongPressPopup(columnHeaderView, tableView)
            popup.show()
        }
    }

    override fun onRowHeaderClicked(rowHeaderView: RecyclerView.ViewHolder, row: Int) {
        showToast("Row header $row has been clicked.")
    }

    override fun onRowHeaderDoubleClicked(rowHeaderView: RecyclerView.ViewHolder, row: Int) {
        showToast("Row header $row has been double clicked.")
    }

    override fun onRowHeaderLongPressed(rowHeaderView: RecyclerView.ViewHolder, row: Int) {
        val popup = RowHeaderLongPressPopup(rowHeaderView, tableView)
        popup.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

package com.example.tableview.table.view.popup

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.tableview.R
import com.example.tableview.table.adapter.AbstractTableAdapter
import com.example.tableview.table.view.ITableView
import com.example.tableview.table.view.TableView

class RowHeaderLongPressPopup(
    viewHolder: RecyclerView.ViewHolder,
    private val tableView: ITableView
) : PopupMenu(viewHolder.itemView.context, viewHolder.itemView),
    PopupMenu.OnMenuItemClickListener {

    companion object {
        private const val SCROLL_COLUMN = 1
        private const val SHOWHIDE_COLUMN = 2
        private const val REMOVE_ROW = 3
    }

    private val rowPosition: Int = viewHolder.adapterPosition

    init {
        initialize()
    }

    private fun initialize() {
        createMenuItem()
        setOnMenuItemClickListener(this)
    }

    private fun createMenuItem() {
        val context: Context = tableView.context
        menu.add(Menu.NONE, SCROLL_COLUMN, 0, context.getString(R.string.scroll_to_column_position))
        menu.add(Menu.NONE, SHOWHIDE_COLUMN, 1, context.getString(R.string.show_hide_the_column))
        menu.add(Menu.NONE, REMOVE_ROW, 2, "Remove $rowPosition position")
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            SCROLL_COLUMN -> tableView.scrollToColumnPosition(15)

            SHOWHIDE_COLUMN -> {
                val column = 1
                if (tableView.isColumnVisible(column)) {
                    tableView.hideColumn(column)
                } else {
                    tableView.showColumn(column)
                }
            }

            REMOVE_ROW -> {
                (tableView.adapter as? AbstractTableAdapter<*, *, *>)?.removeRow(rowPosition)
            }
        }
        return true
    }
}

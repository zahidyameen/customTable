package com.example.tableview.table.view.popup

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.annotation.NonNull
import com.example.tableview.R
import com.example.tableview.table.sort.SortState
import com.example.tableview.table.view.ITableView
import com.example.tableview.table.view.TableView
import com.example.tableview.table.view.holder.ColumnHeaderViewHolder

class ColumnHeaderLongPressPopup(
    @NonNull viewHolder: ColumnHeaderViewHolder,
    @NonNull private val tableView: ITableView
) : PopupMenu(viewHolder.itemView.context, viewHolder.itemView), PopupMenu.OnMenuItemClickListener {

    companion object {
        private const val ASCENDING = 1
        private const val DESCENDING = 2
        private const val HIDE_ROW = 3
        private const val SHOW_ROW = 4
        private const val SCROLL_ROW = 5
    }

    private val xPosition: Int = viewHolder.adapterPosition

    init {
        initialize()
    }

    private fun initialize() {
        createMenuItem()
        changeMenuItemVisibility()
        setOnMenuItemClickListener(this)
    }

    private fun createMenuItem() {
        val context: Context = tableView.context
        menu.add(Menu.NONE, ASCENDING, 0, context.getString(R.string.sort_ascending))
        menu.add(Menu.NONE, DESCENDING, 1, context.getString(R.string.sort_descending))
        menu.add(Menu.NONE, HIDE_ROW, 2, context.getString(R.string.hiding_row_sample))
        menu.add(Menu.NONE, SHOW_ROW, 3, context.getString(R.string.showing_row_sample))
        menu.add(Menu.NONE, SCROLL_ROW, 4, context.getString(R.string.scroll_to_row_position))
        menu.add(Menu.NONE, SCROLL_ROW, 5, "change width")
        // add new ones as needed
    }

    private fun changeMenuItemVisibility() {
        when (tableView.getSortingStatus(xPosition)) {
            SortState.DESCENDING -> menu.getItem(1).isVisible = false
            SortState.ASCENDING -> menu.getItem(0).isVisible = false
            else -> {
                // All visible
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            ASCENDING -> tableView.sortColumn(xPosition, SortState.ASCENDING)
            DESCENDING -> tableView.sortColumn(xPosition, SortState.DESCENDING)
            HIDE_ROW -> tableView.hideRow(5)
            SHOW_ROW -> tableView.showRow(5)
            SCROLL_ROW -> tableView.scrollToRowPosition(5)
        }
        return true
    }
}

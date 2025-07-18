package com.example.tableview.table.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.tableview.R
import com.example.tableview.table.adapter.AbstractTableAdapter
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tableview.table.sort.SortState
import com.example.tableview.table.view.holder.CellViewHolder
import com.example.tableview.table.view.holder.ColumnHeaderViewHolder
import com.example.tableview.table.view.holder.GenderCellViewHolder
import com.example.tableview.table.view.holder.MoodCellViewHolder
import com.example.tableview.table.view.holder.RowHeaderViewHolder
import com.example.tableview.table.view.model.Cell
import com.example.tableview.table.view.model.ColumnHeader
import com.example.tableview.table.view.model.RowHeader

class TableViewAdapter(
    @NonNull private val tableViewModel: TableViewModel2
) : AbstractTableAdapter<ColumnHeader, RowHeader, Cell>() {

    companion object {
        private const val MOOD_CELL_TYPE = 1
        private const val GENDER_CELL_TYPE = 2
        private val LOG_TAG = TableViewAdapter::class.java.simpleName
    }

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        Log.e(LOG_TAG, "onCreateCellViewHolder has been called")
        val inflater = LayoutInflater.from(parent.context)
//        val layout: View = when (viewType) {
//            MOOD_CELL_TYPE, GENDER_CELL_TYPE -> {
//                inflater.inflate(R.layout.table_view_image_cell_layout, parent, false)
//            }
//            else -> {
     val layout=inflater.inflate(R.layout.table_view_cell_layout, parent, false)
//            }
//        }

        return  CellViewHolder(layout)
//        when (viewType) {
//            MOOD_CELL_TYPE -> MoodCellViewHolder(layout)
//            GENDER_CELL_TYPE -> GenderCellViewHolder(layout)
//            else ->

//        }
    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cellItemModel: Cell?,
        columnPosition: Int,
        rowPosition: Int
    ) {
//        when (holder.itemViewType) {
//            MOOD_CELL_TYPE -> {
//                val moodHolder = holder as MoodCellViewHolder
//
//                moodHolder.cellImage.setImageResource(tableViewModel.getDrawable(cellItemModel?.data as Int, false))
//            }
//            GENDER_CELL_TYPE -> {
//                val genderHolder = holder as GenderCellViewHolder
//                genderHolder.cellImage.setImageResource(tableViewModel.getDrawable(cellItemModel?.data as Int, true))
//            }
//            else -> {
                val cellHolder = holder as CellViewHolder
                cellHolder.setCell(cellItemModel)
//            }
//        }
    }

    override fun onCreateColumnHeaderViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_column_header_layout, parent, false)
        return ColumnHeaderViewHolder(layout, tableView)
    }

    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder,
        columnHeaderItemModel: ColumnHeader?,
        columnPosition: Int
    ) {
        val columnHolder = holder as ColumnHeaderViewHolder
        columnHolder.setColumnHeader(columnHeaderItemModel)
    }

    override fun onCreateRowHeaderViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_row_header_layout, parent, false)
        return RowHeaderViewHolder(layout)
    }

    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder,
        rowHeaderItemModel: RowHeader?,
        rowPosition: Int
    ) {
        val rowHolder = holder as RowHeaderViewHolder
        rowHolder.rowHeaderTextView.text = rowHeaderItemModel?.data.toString()
    }

    override fun onCreateCornerView(parent: ViewGroup): View {
        val corner = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_corner_layout, parent, false)

        corner.setOnClickListener {
            val sortState = tableView.rowHeaderSortingStatus
            if (sortState != SortState.ASCENDING) {
                Log.d(LOG_TAG, "Order Ascending")
                tableView.sortRowHeader(SortState.ASCENDING)
            } else {
                Log.d(LOG_TAG, "Order Descending")
                tableView.sortRowHeader(SortState.DESCENDING)
            }
        }

        return corner
    }

    override fun getColumnHeaderItemViewType(position: Int): Int = 0

    override fun getRowHeaderItemViewType(position: Int): Int = 0

    override fun getCellItemViewType(column: Int): Int {
        return when (column) {
            TableViewModel.MOOD_COLUMN_INDEX -> MOOD_CELL_TYPE
            TableViewModel.GENDER_COLUMN_INDEX -> GENDER_CELL_TYPE
            else -> 0
        }
    }
}

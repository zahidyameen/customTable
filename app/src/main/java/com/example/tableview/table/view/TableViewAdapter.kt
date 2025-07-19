package com.example.tableview.table.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tableview.R
import com.example.tableview.table.adapter.AbstractTableAdapter
import com.example.tableview.table.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tableview.table.view.holder.CellViewHolder
import com.example.tableview.table.view.holder.ColumnHeaderViewHolder
import com.example.tableview.table.view.holder.RowHeaderViewHolder
import com.example.tableview.table.view.model.Cell
import com.example.tableview.table.view.model.ColumnHeader
import com.example.tableview.table.view.model.RowHeader

class TableViewAdapter() : AbstractTableAdapter<ColumnHeader, RowHeader, Cell>() {

    companion object {
        private const val MOOD_CELL_TYPE = 1
        private const val GENDER_CELL_TYPE = 2
        private val LOG_TAG = TableViewAdapter::class.java.simpleName
    }

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        Log.e(LOG_TAG, "onCreateCellViewHolder has been called")
        val inflater = LayoutInflater.from(parent.context)
        val layout = when(viewType){
            1,2->{
                inflater.inflate(R.layout.table_view_image_cell_layout, parent, false)
            }
            else -> {
                inflater.inflate(R.layout.table_view_cell_layout, parent, false)
            }

        }
        return when (viewType) {
            MOOD_CELL_TYPE -> MoodCellViewHolder(layout)
            GENDER_CELL_TYPE -> GenderCellViewHolder(layout)
            else -> CellViewHolder(layout)
        }

    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cellItemModel: Cell?,
        columnPosition: Int,
        rowPosition: Int
    ) {

        when (holder.itemViewType) {
            MOOD_CELL_TYPE -> {
                val moodHolder = holder as MoodCellViewHolder

                moodHolder.cellImage.setImageResource(
                    R.drawable.ic_happy
                )
            }

            GENDER_CELL_TYPE -> {
                val genderHolder = holder as GenderCellViewHolder
                genderHolder.cellImage.setImageResource(
                    R.drawable.ic_female
                )
            }

            else -> {
                val cellHolder = holder as CellViewHolder
                cellHolder.setCell(cellItemModel)
            }
        }
    }

    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
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

        return corner
    }

    override fun getColumnHeaderItemViewType(position: Int): Int = 0

    override fun getRowHeaderItemViewType(position: Int): Int = 0

    override fun getCellItemViewType(cell: Cell): Int {
        return when (cell.type) {
            1 -> MOOD_CELL_TYPE
            2 -> GENDER_CELL_TYPE
            else -> 0
        }
    }

    open class MoodCellViewHolder(itemView: View) : AbstractViewHolder(itemView) {

        val cellImage: ImageView = itemView.findViewById(R.id.cell_image)

        open fun setData(data: Any?) {
            val mood = data as? Int ?: return
            val moodDrawable = if (mood == 0) {
                R.drawable.ic_happy
            } else {
                R.drawable.ic_sad
            }

            cellImage.setImageResource(moodDrawable)
        }
    }

    class GenderCellViewHolder(itemView: View) : MoodCellViewHolder(itemView) {

        override fun setData(data: Any?) {
            val gender = data as? Int ?: return
            val genderDrawable = if (gender == 0) {
                R.drawable.ic_male
            } else {
                R.drawable.ic_female
            }

            cellImage.setImageResource(genderDrawable)
        }
    }


}
